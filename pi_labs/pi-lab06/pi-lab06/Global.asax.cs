using Ninject;
using Ninject.Modules;
using Ninject.Web.Mvc;
using pi_lab06.Util;
using System.Web.Mvc;
using System.Web.Routing;

namespace pi_lab06
{
    public class MvcApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();
            RouteConfig.RegisterRoutes(RouteTable.Routes);

            NinjectModule registrations = new NinjectRegistrations();
            var kernel = new StandardKernel(registrations);
            DependencyResolver.SetResolver(new NinjectDependencyResolver(kernel));
        }
    }
}
