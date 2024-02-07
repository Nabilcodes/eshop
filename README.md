REFLECTION 1 :

Clean Code Principles :
- use meaningful names : service.findByNameAndDelete(productName)

Mistake : 
- Instead of editing the product, the current approach for edit feature actually consist of finding the product, deleting the product and making new product instead.
- The above should've been corrected by the use of proper http 'put' method.
