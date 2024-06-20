# Assignment 5

## Assignment 5.1 (Comparison)

### ArrayList vs LinkedList

Both are implement of the list interface and maintain insertion order, also non-syncronized classes.

`ArrayList` is a resizable array implementation of list interface that the array dynamically can grow as needed to accommodate more elements.

`LinkedList` is a doubly implementation of the list and deque interface that consists of the nodes where the node have reference to the next or previous node.

#### Example of using ArrayList and LinkedList

| ArrayList | LinkedList |
| - | - |
| Implement list of items that need to access frequently by index | Implement doubly-ended dequeue where need to add and remove elements from both ends |
| Implement stack `LIFO` or queue `FIFO` | implement that elements where frequent insertions and deletions are required especially in the middle |
| Implement that data doesn't change frequently | Implement complex data structure like graphs or adjacency lists |

### HashSet vs TreeSet vs LinkedHashSet

| | HashSet | TreeSet | LinkedHashSet |
| - | - | - | - |
| Order | Not maintain of order | Order according by natural ordering or specified comparator | Insertion order |
| Null Element | Allow one null element | Doesn't allow null element | Allow one null element |
| Performance (add, remove, contains) | Hash table structure O(1) | Traversing tree structure O(log n) | Hash table structure O(1) |
| Syncronized | Not Syncronized | Not Syncronized | Not Syncronized |
| Iterator | Fail-fast | Fail-fast | Fail-fast |

#### Example of using HashSet, TreeSet, LinkedHashSet

1. `HashSet` use this when no need to maintain order of elements, fast performance or need to ensure uniqueness.
    - Tracking of unique usernames in system.
    - Implenting cache that need insertion speed
2. `TreeSet` use this when need maintain order and unique of elements or fine with slower performance
    - Storing names in alphabetical order.
    - Managing leaderboard that score will sorted from highest to lowest.
3. `LinkedHashSet` use this when prevent order insertion, fast performance, and uniqueness.
    - Implementing queue order by arrival.
    - Keeping log event order by occured.

## Assignment 5.2 (Retrieve an Element)

`The code can be run from the src source from this folder.`

![Retrieve Element from Index](img/Lab5_2.png)

![Retrieve Element from Index but Failed](img/Lab5_2%20Failed.png)

## Assignment 5.3 (Remove Duplicated Data)

`The code can be run from the src source from this folder.`

![Data before remove uniqueness](img/Lab5_3%20ImportData.png)

![Data after remove uniqueness](img/Lab5_3%20OutputData.png)

## Assignment 5.4 ()

## Assignment 5.5 (Convert List to Map)

`The code can be run from the src source from this folder.`

![Convert List to Map Before and After](img/Lab5_5%20Convert%20List%20to%20Map.png)

## Assignment 5.6 ()

## Assignment 5.7 ()