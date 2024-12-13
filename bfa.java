import java.util.Scanner;

/**
 * This class implements the Best Fit memory allocation algorithm.
 * It allocates memory blocks to processes based on their size requirements,
 * ensuring minimal waste of memory space.
 */
public class bfa {
    /**
     * Allocates memory to blocks according to the Best Fit algorithm.
     * 
     * @param blockSizes Array of available memory block sizes
     * @param m Number of memory blocks
     * @param processSizes Array of process sizes that require memory
     * @param n Number of processes
     */
    static void bestFit(int blockSizes[], int m, int processSizes[], int n) {
        // Stores block id of the block allocated to a process
        int allocation[] = new int[n];

        // Initially, no block is assigned to any process
        for (int i = 0; i < allocation.length; i++) 
            allocation[i] = -1;

        // Pick each process and find suitable blocks according to its size
        for (int i = 0; i < n; i++) {
            // Find the best fit block for the current process
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    if (bestIdx == -1) 
                        bestIdx = j;
                    else if (blockSizes[bestIdx] > blockSizes[j])
                        bestIdx = j;
                }
            }

            // If we could find a block for the current process
            if (bestIdx != -1) {
                // Allocate block j to process i
                allocation[i] = bestIdx;
                // Reduce available memory in this block
                blockSizes[bestIdx] -= processSizes[i];
            }
        }

        // Print the allocation results
        System.out.println("\nProcess No.\tProcess Size (KB)\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSizes[i] + " KB\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }

        // Display remaining free memory in each block
        System.out.println("\nFree Memory in Each Block (KB):");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + ": " + blockSizes[i] + " KB");
        }
    }

    /**
     * Driver method to execute the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for block sizes
        int m = 0;
        while (m <= 0) {
            System.out.print("Enter number of memory blocks (positive integer): ");
            m = scanner.nextInt();
            if (m <= 0) {
                System.out.println("Error: Number of memory blocks must be positive.");
            }
        }

        int[] blockSizes = new int[m];
        System.out.println("Enter sizes of memory blocks (in KB):");
        for (int i = 0; i < m; i++) {
            int size = 0;
            while (size <= 0) {
                System.out.print("Block " + (i + 1) + ": ");
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Error: Block size must be positive.");
                }
            }
            blockSizes[i] = size;
        }

        // Input for process sizes
        int n = 0;
        while (n <= 0) {
            System.out.print("Enter number of processes (positive integer): ");
            n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Error: Number of processes must be positive.");
            }
        }

        int[] processSizes = new int[n];
        System.out.println("Enter sizes of processes (in KB):");
        for (int i = 0; i < n; i++) {
            int size = 0;
            while (size <= 0) {
                System.out.print("Process " + (i + 1) + ": ");
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Error: Process size must be positive.");
                }
            }
            processSizes[i] = size;
        }

        // Call the best fit method
        bestFit(blockSizes, m, processSizes, n);
        scanner.close();
    }
}
