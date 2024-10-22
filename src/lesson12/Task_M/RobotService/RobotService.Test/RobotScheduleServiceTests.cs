using NUnit.Framework;
using System.Linq;
using System;
using RobotService;

namespace RobotService.Test
{
    public class RobotScheduleServiceTests
    {
        private RobotScheduleService _service;

        [SetUp]
        public void Setup()
        {
            _service = new RobotScheduleService();
        }

        [Test]
        public void AddCleaningTask_ShouldAddTask_WhenValidTaskProvided()
        {
            var task = new CleaningTask { Room = "Living Room", Time = DateTime.Now.AddHours(1) };
            var result = _service.AddCleaningTask(task);

            Assert.IsTrue(result);
            Assert.Contains(task, _service.GetAllTasks());
        }

        [Test]
        public void AddCleaningTask_ShouldThrowException_WhenTaskTimeIsInThePast()
        {
            var task = new CleaningTask { Room = "Living Room", Time = DateTime.Now.AddHours(-1) };

            Assert.Throws<InvalidOperationException>(() => _service.AddCleaningTask(task));
        }

        [Test]
        public void GetTasksForDay_ShouldReturnTasks_WhenTasksExistForThatDay()
        {
            var task1 = new CleaningTask { Room = "Kitchen", Time = DateTime.Now.AddHours(1) };
            var task2 = new CleaningTask { Room = "Bedroom", Time = DateTime.Now.AddHours(2) };

            _service.AddCleaningTask(task1);
            _service.AddCleaningTask(task2);

            var tasks = _service.GetTasksForDay(DateTime.Today);

            Assert.AreEqual(2, tasks.Count);
            Assert.Contains(task1, tasks);
            Assert.Contains(task2, tasks);
        }

        [Test]
        public void RemoveCleaningTask_ShouldRemoveTask_WhenValidTaskProvided()
        {
            var task = new CleaningTask { Room = "Living Room", Time = DateTime.Now.AddHours(1) };
            _service.AddCleaningTask(task);

            var result = _service.RemoveCleaningTask(task);

            Assert.IsTrue(result);
            Assert.IsFalse(_service.GetAllTasks().Contains(task));
        }

        [Test]
        public void AddCleaningTask_ShouldThrowException_WhenMaxTasksForDayReached()
        {
            for (int i = 0; i < 3; i++)
            {
                var task = new CleaningTask { Room = $"Room {i}", Time = DateTime.Now.AddHours(i + 1) };
                _service.AddCleaningTask(task);
            }

            var newTask = new CleaningTask { Room = "Extra Room", Time = DateTime.Now.AddHours(4) };

            Assert.Throws<InvalidOperationException>(() => _service.AddCleaningTask(newTask));
        }

        [Test]
        public void UpdateCleaningTask_ShouldUpdateTask_WhenValidChangesProvided()
        {
            var task = new CleaningTask { Room = "Living Room", Time = DateTime.Now.AddHours(1) };
            _service.AddCleaningTask(task);

            task.Room = "Updated Room";
            _service.UpdateCleaningTask(task);

            var updatedTask = _service.GetAllTasks().First(t => t.Time == task.Time);

            Assert.AreEqual("Updated Room", updatedTask.Room);
        }

        [Test]
        public void AddCleaningTask_ShouldThrowException_WhenTaskIsEmpty()
        {
            var task = new CleaningTask { Room = "", Time = DateTime.Now.AddHours(1) };

            Assert.Throws<ArgumentException>(() => _service.AddCleaningTask(task));
        }

        [Test]
        public void GetAllTasks_ShouldReturnTasksSortedByTime()
        {
            var task1 = new CleaningTask { Room = "Kitchen", Time = DateTime.Now.AddHours(2) };
            var task2 = new CleaningTask { Room = "Living Room", Time = DateTime.Now.AddHours(1) };

            _service.AddCleaningTask(task1);
            _service.AddCleaningTask(task2);

            var tasks = _service.GetAllTasks();

            Assert.AreEqual(task2, tasks[0]);
            Assert.AreEqual(task1, tasks[1]);
        }

        [Test]
        public void UpdateCleaningTask_ShouldThrowException_WhenTaskDoesNotExist()
        {
            var nonExistentTask = new CleaningTask { Room = "Nonexistent Room", Time = DateTime.Now.AddHours(3) };

            Assert.Throws<InvalidOperationException>(() => _service.UpdateCleaningTask(nonExistentTask));
        }
    }
}
