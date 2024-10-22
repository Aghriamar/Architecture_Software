namespace RobotService
{
    public class RobotScheduleService
    {
        private readonly List<CleaningTask> _tasks = new List<CleaningTask>();
        private const int MaxTasksPerDay = 3;

        public bool AddCleaningTask(CleaningTask task)
        {
            ValidateTask(task);

            if (_tasks.Count(t => t.Time.Date == task.Time.Date) >= MaxTasksPerDay)
                throw new InvalidOperationException("Maximum tasks for the day reached.");

            _tasks.Add(task);
            return true;
        }

        public bool RemoveCleaningTask(CleaningTask task)
        {
            return _tasks.Remove(task);
        }

        public void UpdateCleaningTask(CleaningTask updatedTask)
        {
            var existingTask = _tasks.FirstOrDefault(t => t.Time == updatedTask.Time);
            if (existingTask == null)
                throw new InvalidOperationException("Task not found.");

            existingTask.Room = updatedTask.Room;
        }

        public List<CleaningTask> GetAllTasks()
        {
            return _tasks.OrderBy(t => t.Time).ToList();
        }

        public List<CleaningTask> GetTasksForDay(DateTime day)
        {
            return _tasks.Where(t => t.Time.Date == day.Date).ToList();
        }

        // Вынесенная проверка на валидность задачи
        private void ValidateTask(CleaningTask task)
        {
            if (string.IsNullOrWhiteSpace(task.Room) || task.Time == DateTime.MinValue)
                throw new ArgumentException("Invalid task data.");

            if (task.Time < DateTime.Now)
                throw new InvalidOperationException("Cannot add a task in the past.");
        }
    }
}
