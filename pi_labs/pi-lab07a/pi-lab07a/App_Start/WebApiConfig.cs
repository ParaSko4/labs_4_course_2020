using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace pi_lab07a
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Конфигурация и службы веб-API

            // Маршруты веб-API
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/TS/{id}",
                defaults: new {id = RouteParameter.Optional }
            );
        }
    }
}
