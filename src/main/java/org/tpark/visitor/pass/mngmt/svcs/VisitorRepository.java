package org.tpark.visitor.pass.mngmt.svcs;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tpark.visitor.pass.mngmt.svcs.model.VisitorEntity;
@Component
public class VisitorRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public long savePass(VisitorEntity visitor){
		visitor.setIssuedDate(new Date());
		em.persist(visitor);
		em.flush();
		return visitor.getId();
	}
 	
 
	@Transactional
	 public void saveImage(VisitorEntity visitor){
		//NOT ALL TABLE MAPPED IN JPA:TODO
	    	Query query = em.createNativeQuery("INSERT INTO visitor_image (ID, IMAGE) VALUES(?,?)");
	    	String imageStr=  visitor.getImageencodestr().substring(visitor.getImageencodestr().indexOf(',')+1, visitor.getImageencodestr().length());
	        query.setParameter(1, visitor.getId());
	        query.setParameter(2, imageStr);
	        query.executeUpdate();
	        em.flush();
	    }
}
