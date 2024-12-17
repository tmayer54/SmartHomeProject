/**
 * @file Task.java
 * @date 14/12/2022
 * Describes a task done by a member of the house
 */
package model;

public record Task(int ID, String name, String message, int stamina, int money, int energy) {

}