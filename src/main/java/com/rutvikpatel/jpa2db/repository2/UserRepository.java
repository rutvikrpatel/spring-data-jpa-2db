package com.rutvikpatel.jpa2db.repository2;

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

import com.rutvikpatel.jpa2db.bean2.User;

/**
* <h1>User Repository Interface</h1>
* This is simple User Spring Data repository interface for User bean.
*
* @author  RutvikPatel
* @version 1.0
* @since   20-07-2017
*/

@Transactional
@Repository
@Service
public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User, Integer>{}


/**
* <h1>User Controller Class</h1>
* This is simple controller class for User bean.
*
* @author  RutvikPatel
* @version 1.0
* @since   20-07-2017
*/

@RestController
@RequestMapping(value="/user")
class UserController{
	@Value("${server.address}")
	public String serverAddress;

    @Value("${server.port}")
    public String serverPort;
    
	private UserRepository uRepository;
	@Autowired
	public void setPserosnRepository(UserRepository uRepository) {
		this.uRepository = uRepository;
	}
	
	/**
	* This method is used to get all User bean.
	* 
	* @return List<User> This returns List of User bean.
	*/
	@RequestMapping(value="/get", produces="application/json")
	public List<User> getAllUser(){
		
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Getting All Data..." );
		
		return uRepository.findAll();
	}
	
	/**
	* This method is used to get id specific User bean.
	* 
	* @param Integer This is user id of that User bean. 
	* @return User This returns User bean of matching id.
	*/
	@RequestMapping(value="/get/{id}", produces="application/json")
	public User user(
			@PathVariable(value = "id",required = true) Integer id){
		
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Getting Data Of Id : "+ id );
		
		User bean  = uRepository.findOne(id);
		System.out.println("...sending ID : "+bean.getId()+" Name : "+ bean.getName());
		
		return bean;
	}

	/**
	* This method is used to add User bean.
	* 
	* @param String This is user name of that User bean.
	* @return User This returns User bean of added data.
	*/
	@RequestMapping(value="/add", produces="application/json")
	public User addUser(
			@RequestParam(value = "name", required=true) String name){
		
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Adding New Data : " + name );
		
		User bean = new User();
		bean.setName(name);
		uRepository.save(bean);
		return bean;
	}

	/**
	* This method is used to update User bean.
	* 
	* @param Integer This is user id of that User bean.
	* @param String This is user name of that User bean.
	* @return User This returns User bean of added data.
	*/
	@RequestMapping(value="/update", produces="application/json")
	public User addUser(
			@RequestParam(value = "id", required=true) Integer id,
			@RequestParam(value = "name", required=true) String name){
		
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Updateing Data Of Id : " + id + " Name : "+ name);
		
		User bean = new User();
		bean.setId(id);
		bean.setName(name);
		uRepository.save(bean);
		return bean;
	}

	/**
	* This method is used to delete User bean.
	* 
	* @param Integer This is user id of that User bean.
	*/
	@RequestMapping(value="/delete/{id}", produces="application/json")
	public void deleteUser(
			@PathVariable(value = "id", required = true) Integer id){
		
		System.out.println( "Host : " + serverAddress + " Port : " + serverPort );
		System.out.println( "Deleteing Data Of Id : " + id );
		
		uRepository.delete(id);
	}
}