using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace pi_lab01a
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.IgnoreRoute("shv/get/{*path}");
            routes.IgnoreRoute("shv/post/{*path}");
            routes.IgnoreRoute("shv/put/{*path}");
            routes.IgnoreRoute("shv/postxy/{*path}");
            routes.IgnoreRoute("shv/getpost/{*path}");

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
