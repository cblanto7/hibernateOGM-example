package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.bson.types.ObjectId;

@Entity
@Table(name = "Customers")
public class Customer {

  private ObjectId id;
  private String firstName;
  private String lastName;
  private CustomerGroup customerGroup;

  @Id
  public ObjectId getId() { return id; }
  public void setId(ObjectId id) { this.id = id; }



  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  protected Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @ManyToOne
  public CustomerGroup getCustomerGroup() { return customerGroup; }
  public void setCustomerGroup(CustomerGroup customerGroup) { this.customerGroup = customerGroup; }


  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}