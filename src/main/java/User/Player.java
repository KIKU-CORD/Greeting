package User;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name, message, rank, id;
	
	public Player() {
		name = "";
		id = "";
		message = "";
		rank = "";
	}
	
	public void setName(String name) {
		this.name = name;}
	
	public void setID(String id) {
		this.id = id;}
	
	public void setMessage(String message) {
		this.message = message;}
	
	public void setRank(String rank) {
		this.rank = rank;}
	
	public String getName() {
		return name;}
	
	public String getID() {
		return id;}
	
	public String getMessage() {
		return message;}
	
	public String getRank() {
		return rank;}
}
