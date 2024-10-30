import org.junit.Assert;
import org.junit.Test;

import se.Fredrik.adventure.model.Resident;
import se.Fredrik.adventure.model.Entity;

public class EntityTest {

@Test
public void testPunch(){

    Entity puncher = new Resident(100, 20, "puncher");
    Entity toBePunched = new Resident(100, 20, "toBePunched");

    puncher.punch(toBePunched);

    Assert.assertEquals(80, toBePunched.getHealth());

    }

    @Test
    public void testTakeHit(){
    Entity puncher = new Resident(50, 25, "puncher");
    Entity toBePunched = new Resident(50, 25, "toBePunched");

    puncher.punch(toBePunched);

    Assert.assertEquals(25, toBePunched.getHealth());

    }

    @Test
    public void testIsConscious(){
    Entity puncher = new Resident(25, 25, "puncher");
    Entity toBePunched = new Resident(25, 25, "toBePunched");

    puncher.punch(toBePunched);
    Assert.assertFalse(toBePunched.isConscious());

    toBePunched.punch(puncher);
    Assert.assertFalse(toBePunched.isConscious());

    }



}


