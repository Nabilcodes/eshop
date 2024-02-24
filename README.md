REFLECTION MODULE 1 :

Clean Code Principles :
- use meaningful names : service.findByNameAndDelete(productName)

Mistake : 
- Instead of editing the product, the current approach for edit feature actually
  consist of finding the product, deleting the product and making new product instead.
- The above should've been corrected by the use of proper http 'put' method.

REFLECTION MODULE 3 :

In this project, we apply each of the 5 SOLID principles.
   1) Single Responsibility Principle (SRP) :
      
      The previous code had already had a good implementation of the SRP Principle. 
      Nevertheless,  Interface Classes such as CarService.java and ProductService.java
      had too many responsibility. We decided to refactor each into smaller interface
      classes, each into 4 different interfaces, namely CreateService, DeleteService,
      RetrieveService, and UpdateService.
   2) Open Closed Principle (OCP):
      
      The previous code had also shown good implementation of OCP. Example of such
      possession could easily be seen in the class Car.java and Product.java, which
      was simple enough to cover the properties of Car and Product in general, but can
      be easily extended into another more specific variant of Car or Product.
   3) Liskov Substitution Principle (LSP) :
      
      The previous code does not show any significant use of LSP. However, in the
      refactored version of the code, we decided to implement LSP, specifically to
      accommodate implementation of Repositories. We've already had two instance of
      such repository, namely ProductRepository and CarRepository. To accommodate
      further variants of such repository, we make a common interface ItemRepository
      that will serve as point of reference for the implementation of another
      repository. In implementation, we also make use of Java Types to implement these
      changes in the existing code.
   4) Interface Segregation Principle (ISP) :
      
      The previous code had make use of Interface. Unfortunately, we think the
      the interfaces provided were too large and can be reduced further into smaller
      interfaces. This is what we do towards CarService.java and ProductService.java,
      splitting each into 4 different new interfaces making a total of 8 new interfaces.
   5) Dependency Inversion Principle (DIP):
       
      The previous code had make use of DIP, but unfortunately the use as we seen
      previously in CarController.java, it injects CarServiceImpl.java instead of
      the corresponding interface CarService.java. We change this mechanism and, because
      we also change the CarService into 4 different new service, we inject each of
      these 4 new service into the CarController.java
      
