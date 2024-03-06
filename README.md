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

We also gain huge advantage into incorporating SOLID principles to our project, namely :
   1) Single Responsibility Principle (SRP) :
      
      By using single responsibility principle in CarCreateService.java and similar 
      service, we were able to have an implementation that were simpler in terms of 
      capabilities, such as solely only need to create item without the ability to 
      ever change or delete the added item. We do not need to modify previously
      available implementation. 
   2) Open Closed Principle (OCP):
      
      With OCP, such as in Product.java, we were able to make a more specific version         
      of product without the need to modify the Product.java class. Simple class that
      extends the properties of Product.java were sufficient.
   3) Liskov Substitution Principle (LSP) :
      
      With LSP, such as in ProductRepository and ItemRepository, adding new repository would be much easier.
   4) Interface Segregation Principle (ISP) :
      
      With ISP, implementation of simpler functionalities would be easier. We were able to make an implementation that only needs to create product with the CarCreateService, for example.
   5) Dependency Inversion Principle (DIP):
       
      With DIP, changes in implementation (not the interfaces) would not cause problems in CarController, as opposed to if we've had injected the implementation instead.

REFLECTION MODULE 4

> SELF REFLECTION A

Based on the questions proposed by Percival, I do realize that test making does really helps me out. With the completeness of testing over all possible scenario, I managed to monitor all possible error that can ever happen and keep the software development proceess safe, fast, and reliable.

For example, with a correct and properly written test, I can write the code as fast as I want, without the worry to have the code made error because I already had a 'safety net' in the first place (the tests). If I wanted to make sure the functionality of some portion of the code only, I was also enabled to run some of the corresponding test suite. This does really helps in time, improving flexibility and agility. The process of making tests it self (outside the time spent to determine what were the things to test) was also does not take much time since simple test were sufficient and a lot of the test format were similar, allowing code copy-paste with minimal modification.

Also, I still think that I need to improve my test making capability. The things that I can try to achieve such thing may include seeing other codebase to see how tests were done (what function or mechanism were included) and also see a faster yet more solid approach to determine all things to test.

> SELF REFLECTION B (live code example)

The code was already have good suggestions on how the refactoring should be done. But I personally think more error handling should be done on the code. The lack of error handling in the code means that it does not account for situations where the input is incorrect or unexpected conditions occur. This oversight could lead to runtime errors, which are issues that occur while the program is running, which can lead to severe mismanagement. 

To improve the robustness of the code, I think tha addition of error handling mechanisms that can detect and gracefully handle such issues were of great benefit. This approach may involve elementary things such as validating input data, checking for null or invalid values, and implementing exception handling. By addressing these concerns, the code can become more resilient and less prone to unexpected failures, ensuring a more reliable and stable application for users to interact with.

> FIRST PRINCIPLE REFLECTION

I do genuinely think the test that I made already followed FIRST principle, as I would detail below :

A.FAST            : The tests were simple and easy to compute
B.Isolated        : The tests case were independent as it was focusing on single path, happy or unhappy. It also didnt change the state of function as for each test, a setup method to mock real implementation already provided.
C.Repeatable      : Tests were repeatable as it doesnt change any state of functions.
D.Self-Validating : The tests were strict as it have specific criteria of success or fail. For example, a concrete and specific test using assertEqual, assertNull were used for all important and interchanged field.
E.Timely          : Test does cover all possible scenario. I always try to understand the specification and requirement of the programs that want to be implemented first, and for each specific criteria try to enumerate all possible input to the program and its corresponding expected answer.

       

       

       
      
