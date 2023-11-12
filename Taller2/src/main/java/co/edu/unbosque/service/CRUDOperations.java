package co.edu.unbosque.service;

import java.util.List;

public interface CRUDOperations <T> {
	
	public int create(T data);
	public List<T> getAll();

	public int deleteById(Long id);
	
	public int update(Long id, T newData);
	
	public long count();
	
	public boolean exists(Long id);
}
