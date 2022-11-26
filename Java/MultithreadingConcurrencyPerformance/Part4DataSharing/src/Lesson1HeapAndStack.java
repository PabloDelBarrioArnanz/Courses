public class Lesson1HeapAndStack {

    /*
       1. Stack Memory Region
       Each thread has his own stack, here thread saves:
        - Method are called
        - Arguments are called
        - Local variable are stored
        - Local primitive types
        - Local references
       Stack + Instruction Pointer = State of each thread's execution
       Other threads can't access to this local vars

       2. Heap Memory Region
       Objects always are allocated on the heap
       All threads can access to heap memory
       It's governed by the JVM GC
        - Objects stay as long as we have a reference to them
        - Members of classes exist as long as their parent objects exist
        - Static vars stay forever

       Reference != Object
       References can be allocated on the stack -> if are local vars inside a method
       References can be allocated on the heap -> if they are members of a class

       Object are always allocated on the heap
    */
}