# N Queen Problem

The eight queen problem represents one of the most popular puzzles in chess and computer science. The question is simple, how many queens can you place on
*n* x *n* board in such a way that queens do not threaten each other? There are various solutions, and in this case, we use the backtracking algorithm. We want to 
determine what are the differences in performances between the problem solved sequentially, with multiple threads, and using the message passing interface (MPI)
processes.
