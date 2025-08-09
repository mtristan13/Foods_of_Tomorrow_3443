package edu.utsa.cs3443.cobras.model;
/**
 * Represents a single step in a recipe's instructions.
 * This is a pure data class (POJO).
 */
public class Instruction {
    private final int stepNumber;
    private final String description;

    public Instruction(int stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Provides a formatted string
     * @return Formatted instruction string.
     */
    public String getFormattedInstruction() {
        return stepNumber + ". " + description;
    }
}
