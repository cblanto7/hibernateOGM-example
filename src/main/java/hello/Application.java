/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */

package hello;

import hello.CustomerGroup;
import hello.Customer;
import org.bson.types.ObjectId;
//import org.hibernate.ogm.util.impl.Log;
//import org.hibernate.ogm.util.impl.LoggerFactory;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public class Application {

  //private static final Log logger = LoggerFactory.make();

  public static void main(String[] args) {

    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate Core
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");

    //Persist entities the way you are used to in plain JPA
    try {
      tm.begin();
      //logger.infof("About to store Customer and CustomerGroup");
      EntityManager em = emf.createEntityManager();
      CustomerGroup buisness = new CustomerGroup();
      buisness.setName("Comcast");
      em.persist(buisness);
      Customer dina = new Customer();
      dina.setFirstName("Dina");
      ObjectId x = new ObjectId();
      dina.setId(x);
      dina.setCustomerGroup(buisness);
      em.persist(dina);
      ObjectId dinaId = dina.getId();
      em.flush();
      em.close();
      tm.commit();

      //Retrieve your entities the way you are used to in plain JPA
      //logger.infof("About to retrieve Customer and CustomerGroup");
      tm.begin();
      em = emf.createEntityManager();
      dina = em.find(Customer.class, dinaId);
      //logger.infof("Found Customer %s of CustomerGroup %s", dina.getFirstName(), dina.getCustomerGroup().getName());
      em.flush();
      em.close();
      tm.commit();

      emf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}