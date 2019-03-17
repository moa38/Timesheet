package springData;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import springData.domain.Organization;
import springData.domain.User;

public class OrganizationTest {
   Organization organization;

   @Before
   public void setUpOrganization() {
      organization = new Organization("organizationName", "organizationAddress", "4479");
   }

   @Test
   public void newOrganizationIsEmpty() {
      //Checks that default constructor organization works
      organization = new Organization();
      assertNotNull(organization);
      assertThat(organization, Matchers.hasProperty("name", Matchers.isEmptyOrNullString()));
   }

   @Test
   public void organizationHasName() {
      //Checks that organization has the Name property
      assertThat(organization, Matchers.hasProperty("name"));
   }

   @Test
   public void organizationHasAddress() {
      //Checks that organization has the Address property
      assertThat(organization, Matchers.hasProperty("address"));
   }

   @Test
   public void organizationHasContact() {
      //Checks that organization has given contact number
      assertThat(organization, Matchers.hasProperty("contactNumber", Matchers.equalTo("4479")));
   }

   @Test
   public void organizationHasId() {
      //Checks that organization has given ID
      organization.setOrganizationId(78931);
      assertThat(organization, Matchers.hasProperty("organizationId", Matchers.equalTo(78931)));
   }

   @Test
   public void organizationHasNewId() {
      //Checks that organization has a new ID and it is correct
      organization.setOrganizationId(24367);
      assertThat(organization, Matchers.hasProperty("organizationId", Matchers.not(Matchers.equalTo(465868))));
      assertThat(organization, Matchers.hasProperty("organizationId", Matchers.equalTo(24367)));
   }

   @Test
   public void organizationHasUserList() {
      //Checks that organization has a populated list of users
      //Create list and users
      ArrayList<User> userList = new ArrayList<User>();
      User u1 = new User();
      User u2 = new User();

      //Populate list with users
      organization.setUsers(userList);
      organization.addUser(u1);
      organization.addUser(u2);

      //Assert that list has the correct size 2
      assertThat(organization.getUsers(), Matchers.hasSize(2));
      assertThat(organization.getUsers(), Matchers.not(Matchers.hasSize(0)));
   }
}
