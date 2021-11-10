# Easy as ABC puzzle solver
Program that is capable of solving "Easy as ABC puzzle" by using propositional logic.

Rules and the game itself can be accessed here: http://www.tectonicpuzzel.eu/easy-as-abc-puzzle%20techniques.html

In the first and second part the program is capable of checking the correctness of the puzzle and doing some deductions using procedual programming technique.

In the third part the program encodes the problem into propositional logic, and then use the LogicNG library to use a SAT solver to solve the puzzles.
The SAT solver library can be accessed here: https://github.com/logic-ng/LogicNG

To run particular part navigate to the src folder in the command window and write java main \<TEST\> \<test-set\> where action type and specification are arguments.
