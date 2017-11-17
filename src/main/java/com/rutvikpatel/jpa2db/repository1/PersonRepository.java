package com.rutvikpatel.jpa2db.repository1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rutvikpatel.jpa2db.bean1.Person;

/**
* <h1>Person Repository Interface</h1>
* This is simple Person Spring Data repository interface for Person bean.
*
* @author  RutvikPatel
* @version 1.0
* @since   20-07-2017
*/

@Transactional
@Repository
@Service
public interface PersonRepository extends JpaRepository<Person, Integer>, CrudRepository<Person, Integer>{}


/**
* <h1>Person Controller Class</h1>
* This is simple controller class for Person bean.
*
* @author  RutvikPatel
* @version 1.0
* @since   20-07-2017
*/

@RestController
@RequestMapping(value="/person")
class PersonController{
	@Value("${server.address}")
	public String serverAddress;

    @Value("${server.port}")
    public String serverPort;
    
	private PersonRepository pserosnRepository;
	@Autowired
	public void setPserosnRepository(PersonRepository pserosnRepository) {
		this.pserosnRepository = pserosnRepository;
	}
	
	/**
	* This method is used to get all Person bean.
	* 
	* @return List<Person> This returns List of Person bean.
	*/
	@RequestMapping(value="/get", produces="application/json")
	public List<Person> getAllPerson(){
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Getting All Data..." );
		
		return pserosnRepository.findAll();
	}
	
	/**
	* This method is used to get id specific Person bean.
	* 
	* @param Integer This is person id of that Person bean. 
	* @return Person This returns Person bean of matching id.
	*/
	@RequestMapping(value="/get/{id}", produces="application/json")
	public Person person( @PathVariable(value = "id",required = true) Integer id ){
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Getting Data Of Id : "+ id );
		
		Person bean  = pserosnRepository.findOne(id);
		System.out.println("...sending ID : "+bean.getId()+" Name : "+ bean.getName());
		
		return bean;
	}
	
	/**
	* This method is used to add Person bean.
	* 
	* @param String This is person name of that Person bean.
	* @return Person This returns Person bean of added data.
	*/
	@RequestMapping(value="/add", produces="application/json")
	public Person addPerson(
			@RequestParam(value = "name", required=true) String name ){
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Adding New Data : " + name );
		
		Person bean = new Person();
		bean.setName(name);
		pserosnRepository.save(bean);
		return bean;
	}
	
	/**
	* This method is used to update Person bean.
	* 
	* @param Integer This is person id of that Person bean.
	* @param String This is person name of that Person bean.
	* @return Person This returns Person bean of added data.
	*/
	@RequestMapping(value="/update", produces="application/json")
	public Person addPerson(
			@RequestParam(value = "id", required=true) Integer id,
			@RequestParam(value = "name", required=true) String name){
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Updateing Data Of Id : " + id + " Name : "+ name);
		
		Person bean = new Person();
		bean.setId(id);
		bean.setName(name);
		
		return pserosnRepository.save(bean);
	}
	
	/**
	* This method is used to delete Person bean.
	* 
	* @param Integer This is person id of that Person bean.
	*/
	@RequestMapping(value="/delete/{id}", produces="application/json")
	public void deletePerson(
			@PathVariable(value = "id", required = true) Integer id ){
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Deleteing Data Of Id : " + id );
		
		pserosnRepository.delete(id);
	}
}