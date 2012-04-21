package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class District { 
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private int id ;
	@Column( nullable=false)
	private String name ;
	
	private State  objState=null;
	public int getId (){
	return id ; 
	 } 
	public void setId (int id){
	this.id = id;
	 } 
	public String getName (){
	return name ; 
	 } 
	public void setName (String name){
	this.name = name;
	 } 
	public State getObjState (){
	return objState ; 
	 } 
	public void setObjState (State state){
	this.objState = state;
	 } 
	public String toString(){
	 return this.name;
	 } 
	 } 

