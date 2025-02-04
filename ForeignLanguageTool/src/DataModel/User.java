/*
File: User.java
Author: Ethan Swistak
Date: Jul 5, 2019
Purpose:
*/

package DataModel;

import Logic.MotherTree;
import javax.xml.bind.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import org.w3c.dom.*;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.NONE)
class User extends Item {
    
    @XmlElement(name="name")
    String name = "";
    @XmlElement(name="NativeLanguage")
    String nativeLanguage = "";

    private User(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }
    
    

    //method is called on a read operation
    public static User constructObject(Node node) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object retval = unmarshaller.unmarshal(node);
        if(retval instanceof User){
            ((User) retval).setNode(node);
            return (User)retval;
        }else {
            throw new JAXBException("the object" + User.class.getSimpleName() + "did not parse correctly");
        }
    }
    
    //method is called on an update operation
    public void update(){
        super.updateNode(0, name);
        super.updateNode(1, nativeLanguage);
    }
    
    //method is called on a create operation
    public static User createNew() throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(User.class);
        User user = new User();
        Node node = MotherTree.getInstance().getNodes().createElement("User");
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(user, node);
        user.setNode(node);
        return user;
    }
}
