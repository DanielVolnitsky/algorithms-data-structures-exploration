__Description__

Given a set of N objects.
* Union command: connect two objects.
* Find/connected query: is there a path connecting the two objects?

"is connected to" is an equivalence relation:
* Reflexive: p is connected to p.
* Symmetric: if p is connected to q, then q is connected to p.
* Transitive: if p is connected to q and q is connected to r,
then p is connected to r

Connected components. Maximal set of objects that are mutually connected.

__Implementing the operations__

*Find query* - check if two objects are in the same component.  
*Union command* - replace components containing two objects
with their union.

__Goal__  
Design efficient data structure for union-find.
* Number of objects N can be huge.
* Number of operations M can be huge.
* Find queries and union commands may be intermixed.

Steps to developing a usable algorithm.
* Model the problem.
* Find an algorithm to solve it.
* Fast enough? Fits in memory?
* If not, figure out why.
* Find a way to address the problem.
* Iterate until satisfied.
