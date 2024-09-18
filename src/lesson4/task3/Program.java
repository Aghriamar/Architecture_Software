package lesson4.task3;

import javax.xml.crypto.Data;
import java.util.*;

public class Program {
    /**
     * Разработать контракты и компоненты системы "Покупка онлайн билетов на автобус в час пик"
     *
     * 1. Предусловия.
     * 2. Постусловия.
     * 3. Инвариант.
     * 4. Определить абстрактные и конкретные классы.
     * 5. Определить интерфейсы.
     * 6. Реализовать наследование.
     * 7. Выявить компоненты.
     * 8. Разработать Диаграмму компонент используя нотацию UM 2.0. Общая без деталей.
     */

    public static void main(String[] args) {
        try {
            Core core = new Core();
            MobileApp mobileApp = new MobileApp(core.getTicketProvider(), core.getCustomerProvider());
            BusStation busStation = new BusStation(core.getTicketProvider());
            if (mobileApp.buyTicket("1100000221")) {
                mobileApp.searchTicket(new Date());
                Collection<Ticket> tickets = mobileApp.getTickets();

                Optional<Ticket> ticketOptional = tickets.stream().findFirst();
                if(ticketOptional.isPresent()) {
                    Ticket ticket = ticketOptional.get();
                    if (busStation.checkTicket(ticket.getQrcode())) {
                        System.out.println("Клиент успешно прошел в автобус.");
                    }
                } else {
                    System.out.println("Билеты не найдены.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка" + e.getMessage());
        }
    }
}

class Core {
    private final CustomerProvider customerProvider;
    private final TicketProvider ticketProvider;
    private final PaymentProvider paymentProvider;
    private final Database database;

    public Core() {
        database = new Database();
        customerProvider = new CustomerProvider(database);
        paymentProvider = new PaymentProvider();
        ticketProvider = new TicketProvider(database, paymentProvider);
    }

    public TicketProvider getTicketProvider() {
        return ticketProvider;
    }

    public CustomerProvider getCustomerProvider() {
        return customerProvider;
    }
}

/**
 * Покупатель
 */
class Customer {
    private static int counter;
    private final int id;
    private Collection<Ticket> tickets;

    {
        id = ++counter;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }
}

/**
 * Билет
 */
class Ticket {
    private int id;
    private int customerId;
    private Date date;
    private String qrcode;
    private boolean enable = true;

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public String getQrcode() {
        return qrcode;
    }

    public boolean isEnable() {
        return enable;
    }
}

/**
 * База данных
 */
class Database {
    private static int counter;
    private Collection<Ticket> tickets = new ArrayList();
    private Collection<Customer> customers = new ArrayList();

    public Database(){
        customers.add(new Customer());
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public  Collection<Ticket> getTickets() {
        return tickets;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Получить актуальную стоимость билета
     * @return
     */
    public double getTicketAmount() {
        return 45;
    }

    /**
     * Получить идентификатор заявки на покупку билета
     * @param clientId
     * @return
     */
    public int createTicketOrder(int clientId) {
        return ++counter;
    }

    public Customer getCustomerById(int clientId) {
        for (Customer customer : customers) {
            if (customer.getId() == clientId) {
                return customer;
            }
        }
        return null;
    }
}

class PaymentProvider {
    public boolean buyTicket(int orderId, String cardNo, double amount) {
        //TODO: Обращение к платёжному шлюзу, попытка выполнить списание средств ...
        return true;
    }
}

/**
 *
 */
class MobileApp{
    private final Customer customer;
    private final TicketProvider ticketProvider;
    private final CustomerProvider customerProvider;

    public MobileApp(TicketProvider ticketProvider, CustomerProvider customerProvider) {
        this.ticketProvider = ticketProvider;
        this.customerProvider = customerProvider;
        customer = customerProvider.getCustomer("<login>", "<password>");
    }

    public Collection<Ticket> getTickets() {
        return customer.getTickets();
    }

    /**
     * Поиск билетов по дате.
     * @param date дата билета
     */
    public void searchTicket(Date date) {
        // Предусловие
        if(date == null){
            throw new IllegalArgumentException("Дата не может быть нулевой.");
        }
        customer.setTickets(ticketProvider.searchTicket(customer.getId(), date));

        // Постусловие
        if(customer.getTickets().isEmpty()){
            System.out.println("Билеты на указанную дату не найдены.");
        }
    }

    /**
     * Покупка билета через мобильное приложение.
     * @param cardNo номер карты
     * @return true если покупка успешна, иначе false
     */
    public boolean buyTicket(String cardNo) {
        // Предусловие
        if(cardNo == null || cardNo.isEmpty()){
            throw new IllegalArgumentException("Номер карты не может быть нулевым или пустым.");
        }
        return ticketProvider.buyTicket(customer.getId(), cardNo);
    }
}

class  TicketProvider{
    private final Database database;
    private final PaymentProvider paymentProvider;

    public TicketProvider(Database database, PaymentProvider paymentProvider) {
        this.database = database;
        this.paymentProvider = paymentProvider;
    }

    /**
     * Поиск билета по дате и идентификатору клиента.
     * @param clientId идентификатор клиента
     * @param date дата поиска
     * @return коллекция билетов
     * @throws IllegalArgumentException если clientId < 0 или дата равна null
     */
    public Collection<Ticket> searchTicket(int clientId, Date date) {
        // Предусловие
        if(clientId <= 0){
            throw new IllegalArgumentException("Идентификатор клиента должен быть положительным.");
        }
        if (date == null){
            throw new IllegalArgumentException("Дата не может быть нулевой.");
        }

        System.out.println("Поиск билетов. Доступные билеты:");
        for (Ticket ticket : database.getTickets()) {
            System.out.println("Билет ID: " + ticket.getId() + ", QR: " + ticket.getQrcode() + ", Дата: " + ticket.getDate());
        }

        Collection<Ticket> tickets = new ArrayList<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        for (Ticket ticket : database.getTickets()) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(ticket.getDate());
            boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
            if (ticket.getCustomerId() == clientId && sameDay) {
                tickets.add(ticket);
            }
        }

        //Постусловие
        if(tickets.isEmpty()){
            System.out.println("Билеты на указанного клиента и дату не найдены.");
        }
        return tickets;
    }

    /**
     * Покупка билета.
     * @param clientId идентификатор клиента
     * @param cardNo номер карты
     * @return true если покупка прошла успешно, false если нет
     * @throws IllegalArgumentException если номер карты некорректен
     */
    public boolean buyTicket(int clientId, String cardNo) {
        // Предусловие
        if(clientId <= 0){
            throw new IllegalArgumentException("Идентификатор клиента должен быть положительным.");
        }
        if(cardNo == null || cardNo.isEmpty()){
            throw new IllegalArgumentException("Номер карты не может быть нулевым или пустым.");
        }

        int orderId = database.createTicketOrder(clientId);
        double amount = database.getTicketAmount();

        //Постусловие
        boolean result = paymentProvider.buyTicket(orderId, cardNo, amount);
        if(result){
            System.out.println("Покупка билета прошла успешно.");
            Ticket newTicket = new Ticket();
            newTicket.setEnable(true);
            newTicket.setCustomerId(clientId);
            newTicket.setDate(new Date());
            newTicket.setQrcode("QR_" + clientId + "_" + orderId);
            database.addTicket(newTicket);

            System.out.println("Текущие билеты в базе данных:");
            for (Ticket ticket : database.getTickets()) {
                System.out.println("Билет ID: " + ticket.getId() + ", QR: " + ticket.getQrcode());
            }

            Customer customer = database.getCustomerById(clientId);
            if(customer != null){
                Collection<Ticket> customerTickets = customer.getTickets();
                if(customerTickets == null){
                    customerTickets = new ArrayList();
                }
                customerTickets.add(newTicket);
                customer.setTickets(customerTickets);
            } else {
                System.out.println("Клиент не найден");
            }
        } else {
            System.out.println("Покупка билета не удалась.");
        }
        return result;
    }

    /**
     * Проверка билета по QR-коду.
     * @param qrcode QR-код билета
     * @return true если билет найден и действителен, иначе false
     * @throws IllegalArgumentException если qrcode равен null или пуст
     */
    public boolean checkTicket(String qrcode){
        // Предусловие
        if(qrcode == null || qrcode.isEmpty()){
            throw new IllegalArgumentException("QR-код не может быть нулевым или пустым.");
        }
        for (Ticket ticket : database.getTickets()) {
            if(ticket.getQrcode().equals(qrcode)){
                ticket.setEnable(false);
                // Save database ...
                return true;
            }
        }

        // Постусловие
        System.out.println("Билет не найден или уже использован.");
        return false;
    }
}

class CustomerProvider{
    private Database database;

    public CustomerProvider(Database database) {
        this.database = database;
    }

    public Customer getCustomer(String login, String password) {
        Optional<Customer> existingCustomer = database.getCustomers().stream().findFirst();
        if(existingCustomer.isPresent()){
            return existingCustomer.get();
        } else {
            Customer newCustomer = new Customer();
            database.addCustomer(newCustomer);
            return newCustomer;
        }
    }
}

/**
 * Автобусная станция
 */
class BusStation {
    private final TicketProvider ticketProvider;

    public BusStation(TicketProvider ticketProvider) {
        this.ticketProvider = ticketProvider;
    }

    public boolean checkTicket(String qrcode) {
        return ticketProvider.checkTicket(qrcode);
    }
}