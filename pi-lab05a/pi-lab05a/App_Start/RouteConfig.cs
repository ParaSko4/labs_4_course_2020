using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace pi_lab05a
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "t0",
                url: "MResearch/M01/{id}",
                defaults: new { controller = "MResearch", action = "M01", id = UrlParameter.Optional }
            );

            routes.MapRoute(
                name: "t1",
                url: "MResearch/M01/",
                defaults: new { controller = "MResearch", action = "M01" }
            );

            routes.MapRoute(
                name: "t2",
                url: "MResearch/",
                defaults: new { controller = "MResearch", action = "M01" }
            );

            routes.MapRoute(
                name: "t4",
                url: "V2/MResearch/M01",
                defaults: new { controller = "MResearch", action = "M01" }
            );

            routes.MapRoute(
                name: "t5",
                url: "V3/MResearch/X/M01",
                defaults: new { controller = "MResearch", action = "M01" }
            );

            routes.MapRoute(
                name: "t6",
                url: "V2",
                defaults: new { controller = "MResearch", action = "M02" }
            );

            routes.MapRoute(
                name: "t7",
                url: "V2/MResearch",
                defaults: new { controller = "MResearch", action = "M02" }
            );

            routes.MapRoute(
                name: "t8",
                url: "V2/MResearch/M02",
                defaults: new { controller = "MResearch", action = "M02" }
            );

            routes.MapRoute(
                name: "t9",
                url: "MResearch/M02",
                defaults: new { controller = "MResearch", action = "M02" }
            );

            routes.MapRoute(
                name: "t10",
                url: "V3/MResearch/X/M02",
                defaults: new { controller = "MResearch", action = "M02" }
            );

            routes.MapRoute(
                name: "t11",
                url: "V3",
                defaults: new { controller = "MResearch", action = "M03" }
            );

            routes.MapRoute(
                name: "t12",
                url: "V3/MResearch/X",
                defaults: new { controller = "MResearch", action = "M03" }
            );

            routes.MapRoute(
                name: "t13",
                url: "V3/MResearch/X/M03",
                defaults: new { controller = "MResearch", action = "M03" }
            );

            routes.MapRoute(
                name: "ct1",
                url: "CResearch",
                defaults: new { controller = "CResearch", action = "C01" }
            );

            routes.MapRoute(
                name: "ct2",
                url: "CResearch/C01",
                defaults: new { controller = "CResearch", action = "C01" }
            );

            routes.MapRoute(
                name: "ct3",
                url: "CResearch/C02",
                defaults: new { controller = "CResearch", action = "C02" }
            );

            routes.MapRoute(
                "404-PageNotFound",
                "{*url}",
                new { controller = "MResearch", action = "MXX" }
            );
        }
    }
}
