using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MyFirstWebApplication.Models;
using Swashbuckle.AspNetCore.Annotations;
using System.Globalization;

namespace MyFirstWebApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class WeatherForecastController : ControllerBase
    {
        private WeatherForecastHolder _weatherForecastHolder;
        private readonly string _dateFormat = "dd.MM.yyyy";
        public WeatherForecastController(WeatherForecastHolder weatherForecastHolder)
        {
            _weatherForecastHolder = weatherForecastHolder;
        }

        [HttpPost("add")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public IActionResult Add([FromQuery] DateTime date, [FromQuery] int temperatureC)
        {
            try
            {
                if (temperatureC < -100 || temperatureC > 100)
                {
                    return BadRequest(new { status = "error", message = "Invalid temperature value" });
                }

                _weatherForecastHolder.Add(date, temperatureC);
                return Ok(new { status = "created", message = "Resource created successfully" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { status = "error", message = ex.Message });
            }
        }

        [HttpPut("update")]
        [SwaggerResponse(200, "Resource updated successfully")]
        [SwaggerResponse(400, "Forecast for the given date not found")]
        [SwaggerResponse(500, "Internal server error")]
        public IActionResult Update([FromQuery] DateTime date, [FromQuery] int temperatureC)
        {
            try
            {
                bool updated = _weatherForecastHolder.Update(date, temperatureC);
                if (!updated)
                {
                    return BadRequest(new { status = "error", message = "Forecast for the given date not found" });
                }

                return Ok(new { status = "updated", message = "Resource updated successfully" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { status = "error", message = ex.Message });
            }
        }

        [HttpDelete("delete")]
        [SwaggerResponse(200, "Resource deleted successfully")]
        [SwaggerResponse(400, "Forecast for the given date not found")]
        [SwaggerResponse(500, "Internal server error")]
        public IActionResult Delete([FromQuery] DateTime date)
        {
            try
            {
                bool deleted = _weatherForecastHolder.Delete(date);
                if (!deleted)
                {
                    return BadRequest(new { status = "error", message = "Forecast for the given date not found" });
                }

                return Ok(new { status = "deleted", message = "Resource deleted successfully" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { status = "error", message = ex.Message });
            }
        }

        [HttpGet("get")]
        [SwaggerResponse(200, "Data retrieved successfully", typeof(List<WeatherForecast>))]
        [SwaggerResponse(400, "Invalid date range")]
        [SwaggerResponse(500, "Internal server error")]
        public IActionResult Get([FromQuery] DateTime dateFrom, [FromQuery] DateTime dateTo)
        {
            try
            {
                if (dateFrom > dateTo)
                {
                    return BadRequest(new { status = "error", message = "Invalid date range" });
                }

                List<WeatherForecast> list = _weatherForecastHolder.Get(dateFrom, dateTo);
                // Форматирование дат при выводе
                var formattedList = list.Select(forecast => new
                {
                    Date = forecast.Date.ToString(_dateFormat),
                    TemperatureC = forecast.TemperatureC,
                    TemperatureF = forecast.TemperatureF
                }).ToList();

                return Ok(formattedList);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { status = "error", message = ex.Message });
            }
        }
    }
}
