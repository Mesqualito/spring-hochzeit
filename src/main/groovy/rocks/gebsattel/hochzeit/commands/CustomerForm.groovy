package rocks.gebsattel.hochzeit.commands


// use a Command-Object instead of a Domain-Object
// to populate the properties of an User- and a Customer-Object
// so there is a separation from the database-Objects
// and the webform
class CustomerForm {

    Integer userId            // user-property
    Integer userVersion       // user-property
    Integer customerId        // customer-property
    Integer customerVersion   // customer-property
    String userName           // user-property
    String passwordText       // user-property
    String passwordTextConf
    String firstName          // customer-property
    String lastName           // customer-property
    String eMail              // customer-property
    String phoneNr            // customer-property
}
