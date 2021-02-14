using pi_lab07c;
using System;
using System.ServiceModel;

namespace Host
{
    class Program
    {
        static void Main(string[] args)
        {
            using (ServiceHost host = new ServiceHost(typeof(ContactService)))
            {
                host.Open();
                Console.ReadLine();
            }
        }
    }
}