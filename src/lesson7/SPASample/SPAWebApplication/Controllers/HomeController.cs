using Microsoft.AspNetCore.Mvc;
using SPAWebApplication.Models;

namespace SPAWebApplication.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Home() 
        {
            List<Employee> employees = new List<Employee>();
            employees.Add(new Employee
            {
                Age = 30,
                Id = 1001,
                Name = "AAAAAA"
            });
            employees.Add(new Employee
            {
                Age = 41,
                Id = 1002,
                Name = "BBBBBB"
            });
            employees.Add(new Employee
            {
                Age = 35,
                Id = 1003,
                Name = "CCCCCC"
            });
            return View(employees); 
        }
        public IActionResult About()
        {
            return View();
        }
        public IActionResult Contact()
        {
            return View();
        }

    }
}
