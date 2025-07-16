# COMP 271 SU25 WEEK 07

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

### Complete method `void add(E content)`

Your sole task for this assignment is to complete method `add`. If everyone submits this assignment *before* class time on Monday 7/14, we will work together on finishing this very important data structure. If not all assignemnts are submitted on 7/14, the deadline will become 7/16 and the next assignment will require you to finish the rest of the class. That would be a lot of work. We can do it together in class, if everyone submits by 7/14 10 AM.

Method `add` places a new node with the specified content, in the appropriate linked list in the underlying array of class `HashTable`. The location of the linkedlist can be found from the content's hashcode. As demonstrated in class, **every Java object has a hashcode.**  Any object's hashcode can be obtained with method `.hashCode()`. The method returns an **`int`** value. Use this value to determine which position of the underlying array hosts the desired linked list.  For example, the hashcode of the String object `"Chicago"` is -1,884,315,574. For `"CHICAGO"`, the hashcode is 1,463,981,130.

Using the hashcode of the object `content` stored in `Node`, determine its destination to the underlying array. The destination must be a valid position in the array, i.e., any number in the interval $[0,\texttt{this.underlying.length})$. (An integer interval of the form $[A,B)$ with $A<B$ is the sequence of numbers from $A$ inclusive to $B$ exclusive, i.e., $A, A+1, \ldots, B-2, B-1$.

After you obtain the position in the underlying array, check its contents. The designated position may be empty (`null`) or it may already contain a node from an earlier placement. If the designated position is null, just place the new node there. If it is not empty, we need to complete the following steps:
1.  Make the current occupant in the array, the new node's next node.
2.  Place the new node in the array at the designated position.

### Implement a `String toString()` method

Implement the most creative and informative text representation for the `HashTable` object you can think of. You are welcome to add constants and new class attributes to `HashTable` if you feel like, as long as you explain their purpose.

---



## Study

From the BJP textbook Chapter 18.1; or [Ch. 14 from Collins’ book](https://learning.oreilly.com/library/view/data-structures-and/9780470482674/21-chapter14.html). In addition, you should be up to speed with the following.

* Java's [tutorial on Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)
* Java's [Comparable interface](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)
* Chapter 3.1 (about method overloading) (alternatively Java's [tutorial on methods](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html))
* Chapter 8 from the BJP textbook (or at the very least Java’s tutorial on Classes and Objects)
* Chapter 9 from the BJP textbook (or at the very least Java's tutorial on interfaces and inheritance)

---

## Reflect

Compare your code from the previous assignment with [Stack](https://github.com/lgreco/comp-271-su25-week06/blob/main/solution_Stack.java) and [Queue](https://github.com/lgreco/comp-271-su25-week06/blob/main/solution_Queue.java).

Then write a brief reflection (100-300 words) discussing what you got right, what you got close but not quite, and where you may have missed the mark. Also discuss what you learned by comparing your code to the posted solutions. The reflection must be substantive. For example, you may find that you missed something in the assignment because you did not put enough time in it or because you did not start work early. It's fine to acknowledge these issues. It is also important to propose a plan to avoid them in the future. And, in later reflections, evaluate how that plan worked.


### TECHNICAL NOTES FOR UNGRADING


#### `int compareTo`

For both classes (Stack and Queue), the implementation of the `Comparable` interface can involve *either* the different in underlying array lengths betweek the invoking object (`this`) and the referenced object (`other`) *or* the difference between their respective `occupancy` values. Using `occupancy` may be more accurate. Both objects (`this` and `other`) may have the same length underlying arrays, but different number of elements in them. Notice that we cannot access the occupancy value from `Stack` or `Queue` directly, because it's a `private` field. A *getter* method, `int getOccupancy()` is introduced in the superclass `DynamicArray` to facilitate access.

#### `String peek()`

This requires access to the first element of the underlying array in superclass `DynamicArray`. Because the underlying array is `private`, we cannot simply write 
```java
return this.underlying[0];
```
Therefore, we must **update** class `DynamicArray` with an accessor method to return the first element of its underlying array. This is method [`DynamicArray.getFirst()`](https://github.com/lgreco/comp-271-su25-week06/blob/24ac9fdc1c91448ba90eb4a30eb8608d9e1bd6cf/DynamicArray.java#L39).

#### `String pop()`

This is a simple call to `DynamicArray.remove(0)` for both its `Queue` and `Stack` extensions.

#### `void Stack.push(String e)`

This method is specific to the `Stack` extension of `DynamicArray` and is dictacted by interface `Lifo`. It requires that we place the new value `String e` at the front of the underlying array. To do that, we must first shift all the elements from the front, one positiont to the right. This may require resizing the underlying array first. Essentially, we need to [overload `add`](https://github.com/lgreco/comp-271-su25-week06/blob/24ac9fdc1c91448ba90eb4a30eb8608d9e1bd6cf/DynamicArray.java#L79) in `DynamicArray` to allow for addition of an element at a specified index position.

#### No `Queue.add()`?

There is no `add` method in class `Queue`. Still, the class fulfills the `Lifo` interface through its inherited `void DynamicArray.add(String e)`

#### Does your code compile? 
If not, the assignment is incomplete. If your assignment is incomplete you must make an appointment to speak with Leo, as soon as possible.


#### Did your code pass the tests?
If not, the assignment is incomplete. If your assignment is incomplete you must make an appointment to speak with Leo, as soon as possible.


#### Incomplete code

If your code is incomplete for any of the reasons above, please reflect on the cause. Did you start late? Did you hit a roadblock but did not ask for help? Did you not read the Programmer's Pact? Something else?


#### Are there any Pact violations?
You should always check your code versus the Programmer's Pact and verify that you have not violated any of its stipulations.


#### How to submit

Your reflection should be submitted together with the current homework assignment on the stated deadline. Write your reflection as a *markdown* file called `reflection.md` in the current assignment's repository. **MarkDown** is a fairly simple markup (I know!) language that's worth learning. You can use a [simple cheat sheet](https://www.markdownguide.org/basic-syntax/) for MarkDown (.md) files or you can look at the course code of any `.md` file I share with you such as this one here. You can open any `.md` file on your CodeSpaces editor to see how it's written. 

If MarkDown is not your cup of tea that's ok too. Write your reflection in an email and send it to me with the header "reflection" in the subject line. But if you see yourself as a programmer/computer scientist down the road, it's worth the time to learn to write in MarkDown.
