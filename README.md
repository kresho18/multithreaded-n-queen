# N Queen Problem

The eight queen problem represents one of the most popular puzzles in chess and computer science. The question is simple, how many queens can you place on
*n* x *n* board in such a way that queens do not threaten each other? There are various solutions, and in this case, we use the backtracking algorithm. We want to 
determine what are the differences in performances between the problem solved sequentially, with multiple threads, and using the message passing interface (MPI)
processes.

## Parallelization

Parallelization allows us to solve problems of increasing complexity and obtain results faster, and in cases where computation complexity is pretty high, they represent the only choice, since sequential solutions require a lot of time to compute.

### Parallel part

In this part we have a manager/worker paradigm with each worker requesting a row to work with and as soon as it finishes, it requests for a new
placement. We create a worker class and run the solution, but in this case we already know the placement of the queen in the first column, so we
start by placing the queen in the second column. Each thread has a private array for placing the solution, but when the solution occurs it is pushed
to a list, which upon completion is scanned for launching, the GUI.

### Distributed part

For the distributed part we ran 4 processes with the help of the MPJ express library with one process being bound to loading and scattering data for the other 3
processes. The worker processes are waiting for the server process to assign which parts of the chessboard are they going to compute nd then compute them. It means 
that each process gets the rows which they should compute and construct the worker object which calls the solution function for the computation. After the
workers finish we can gather data and show solutions on graphics.

### One of the possible solutions

![Solution example](https://github.com/kresho18/multithreaded-n-queen/blob/master/solution-example.png "Solution example")

## Results

For each parameter, the program was executed three times and the average time was taken for the graph. Measurement began from 8 queens at the time placed on the board until we reached the 10-minute mark as explained in the guide- lines. In our case, that number was 17 queens at the time.

## Technical remarks

To run the distributed part you need to install the mpj express library available [here](http://www.mpjexpress.org/download.php). It is launched with 1.8 JRE and made to work for np -4 processes.
