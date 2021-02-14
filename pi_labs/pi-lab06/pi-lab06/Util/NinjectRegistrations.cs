using InterfaceDLL;
using JsonDataMethods;
using Ninject.Modules;
using SQLiteDataMethods;

namespace pi_lab06.Util
{
    public class NinjectRegistrations : NinjectModule
    {
        public override void Load()
        {
            Bind<IPhoneDictionary>().To<SQLiteClass>();
            //Bind<IPhoneDictionary>().To<JsonClass>().InThreadScope();
        }
    }
}