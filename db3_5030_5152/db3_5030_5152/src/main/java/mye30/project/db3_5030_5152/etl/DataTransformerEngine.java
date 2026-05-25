package mye30.project.db3_5030_5152.etl;

public class DataTransformerEngine {

    public static void main(String[] args) {
        long engineStartTime = System.currentTimeMillis();
        System.out.println("=================================================");
        System.out.println("STARTING GLOBAL ETL DATA TRANSFORMATION ENGINE");
        System.out.println("=================================================\n");

        try {
            System.out.println("[STEP 1/4] Executing JournalDataTransformer...");
            long step1Start = System.currentTimeMillis();
            JournalDataTransformer.main(args);
            System.out.println("Step 1 finished in " + getExecutionTime(step1Start) + "s.\n");

            System.out.println("[STEP 2/4] Executing ConferenceDataTransformer...");
            long step2Start = System.currentTimeMillis();
            ConferenceDataTransformer.main(args);
            System.out.println("Step 2 finished in " + getExecutionTime(step2Start) + "s.\n");

            System.out.println("[STEP 3/4] Executing ArticlesDataTransformer...");
            long step3Start = System.currentTimeMillis();
            ArticlesDataTransformer.main(args);
            System.out.println("Step 3 finished in " + getExecutionTime(step3Start) + "s.\n");

            System.out.println("[STEP 4/4] Executing RankingsAndCategoriesDataTransformer...");
            long step4Start = System.currentTimeMillis();
            RankingsAndCategoriesDataTransformer.main(args);
            System.out.println("Step 4 finished in " + getExecutionTime(step4Start) + "s.\n");

            System.out.println("=================================================");
            System.out.println("ALL DATA TRANSFORMATIONS COMPLETED SUCCESSFULLY!");
            System.out.println("Total Engine Processing Time: " + getExecutionTime(engineStartTime) + " seconds.");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.err.println("\nCRITICAL ERROR: Execution aborted inside pipeline!");
            e.printStackTrace();
        }
    }

    private static double getExecutionTime(long startTime) {
        return (double) (System.currentTimeMillis() - startTime) / 1000.0;
    }
}