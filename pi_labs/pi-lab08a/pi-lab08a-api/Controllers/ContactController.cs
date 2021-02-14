using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using pi_lab08a_api.Data.Repository.Interfaces;
using pi_lab08a_api.Models;

// http://localhost:60950/api/contact

namespace pi_lab08a_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ContactController : ControllerBase
    {
        private IRepositoryBase<ContactModel> contactRepository;

        public ContactController(IRepositoryBase<ContactModel> contactRepository)
        {
            this.contactRepository = contactRepository;
        }

        // GET: api/<ContactController>
        [HttpGet]
        public IEnumerable<ContactModel> Get()
        {
            return contactRepository.GetAll();
        }

        // GET api/<ContactController>/5
        [HttpGet("{id}")]
        public ContactModel Get(int id)
        {
            return contactRepository.Get(id);
        }

        // POST api/<ContactController>
        [HttpPost]
        public void Post([FromBody] ContactModel value)
        {
            contactRepository.Add(value);
        }

        // PUT api/<ContactController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] ContactModel value)
        {
            contactRepository.Update(id, value);
        }

        // DELETE api/<ContactController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            contactRepository.Delete(id);
        }
    }
}
