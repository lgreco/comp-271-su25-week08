# COMP 271 SU25 WEEK 08

This assignment has two parts: a coding part based on current material we discuss in class and a reflection part to evaluate work you have already submitted.


## Standing requirements

* **Programmers Pact:** Your work must be compliant with the [Programmers Pact](./ProgrammerPact.pdf). 
* **Comments:** Your code must be sufficiently documented with comments.
* **No imports:** You may not use the import statement in your code, with one exception: `import Arrays;` is allowed only for using `Arrays.toString()`. No other methods of class `Arrays` may be used.
* **Only `System.out` calls:** you may use `System.out` to print to the console. No other methods from `System` are allowed. Only `System.out.println`, `System.out.printf`, and `System.out.print`. 


## Finals week policy

There is no final exam for the course. There will be a final assignemnt that will be published the week before finals and will be due the week of finals. Additionally, 8 students in the course will be [invited randomly](https://github.com/lgreco/random-selection-final-oral) to a brief meeting with the instructor during the course's final exam slot. The final exam slot for this course is on Tuesday, August 5, 2025 from 9 to 11 AM. If you are selected for a brief meeting, we'll spend about 15 minutes during the final exam slot to review your work. This interview will cover coding practices based on your past assignments. It is meant as a checkpoint to ensure that you have internalized the work you submitted.

---


## Code

You'll work with classes `HashTable.java` and `Node.java`.


### Implement method `boolean contains(E target)`

Complete the existing method `contains` so that it will return `true` when the `target` value is found in the hashtable object and `false` otherwise.


### Modify the object to rehash its data

As demonstrated it class, we want to keep track of how many elements of the underlying array are used. And to double the underlying array when the utilization exceeds a specific threshold.

In class `Hashtable`, we've introducted the following fields and constants:

* `int usage` to count how many array elements are used. 
* `double loadFactor` that tracks the utilization of the underlying array. The load factor is defined as<br/> $\text{load factor} = \dfrac{\text{usage}}{\text{underlying.array.length}}$<br/>
* `final double LOAD_FACTOR_THRESHOLD` to indicate when to rehash the object. When `loadFactor >= LOAD_FACTOR_THRESHOLD` it is time to rehash the object.

To modify the object as demonstrated in class, you need to ensure the following.

* Before adding a new node to the hash table check the object's load factor. 

  * If it is less than it's threshold, add the new node to the object and update its `usage`, `totalNodes`, and `loadFactor` properties.

  * If it's not less than its threshold:

    * Create a temporary array of simple linked lists, twice the size of the underlying array.

    * Go through every node in the underlying array and add it to the temporary array.

    * Replace the underlying array with the temporary array.

    * Compute the new load factor.

    * Now add the new node to the hash table and update `usage`, `loadFactor`, and `totalNodes`.

There are a few ways to accomplish the steps above. Your objective is to *plan and design* an approach that will duplicate code as much as possible. And when you find yourself writing duplicate code, you should consider creating new methods to be called instead.

The analysis, planning, and design of this problem's solution requires some thinking. Put your ideas together before you start coding, otherwise it will get a bit messy.

Do not trust AI-generated solutions if you do not fully understand them. This assignment will most definitely be part of the oral 1-1 planned for the end of the course.

For your modifications you may introduce new class attributes or constants, as well as methods. It is absolutely necessary that you document (with Javadoc comments) what these new members do and why they are needed.

Class `HashTableTest.java` provides simple testing to verify the correctness of your code.

No homework was due for week 07.
