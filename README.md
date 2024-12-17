# AP4B - Smart Home Project

## Authors
- **Timothé Watteau**
- **Thibault Mayer**  
*Université de Technologie de Belfort-Montbéliard (UTBM) - Automne 2022*

---

## 📘 Project Overview

This project involves creating a **Smart Home Simulation Game** in Java. It emphasizes UML diagram design, object-oriented programming, and the use of the **MVC (Model-View-Controller)** architecture.

The game takes place in a house where inhabitants complete daily tasks. The player must manage resources like energy, stamina, and money while improving the house through automation.

---

## 🎮 Game Rules

1. **Setup**:  
   - A house with **2 inhabitants** (extendable).
   - Resources: `Money`, `Stamina`, and `Energy`.
   - Actions are defined daily but can be interrupted by events.

2. **Daily Gameplay**:
   - The player allocates tasks to inhabitants.
   - Tasks consume stamina and affect resources.
   - The player can upgrade the house at the end of the day.

3. **Winning Condition**:  
   - The house remains **viable** (balanced energy, resources, and upgrades).
   - The game ends if resources deplete or the house becomes unviable.

---

## 🗂 UML Diagrams

### Key Diagrams
- **Use Case Diagram**: Defines user actions and game states.
- **Activity Diagram**: Illustrates the day-to-day game flow.
- **Class Diagram**: Follows the **MVC architecture**:
    - `Model`: Core game logic (house, inhabitants, etc.).
    - `View`: User interface components.
    - `Controller (UI)`: Handles game input and interactions.
- **Sequence Diagram**: Describes object interactions during gameplay.

---

## 🛠 Implementation

The game is developed in **Java** with an emphasis on:
- **Modularity**: Using a flexible **Model-View-Controller (MVC)** design.
- **Scalability**: The game can support variable inhabitants, rooms, and upgrades.
- **Adaptability**: User interfaces adjust dynamically to game elements.

---

## 🚀 Project Organization

- **Version Control**: GitHub for collaboration and history tracking.
- **Planning**: UML diagrams create using LucidChart
- 
---

## 🎯 Conclusion

This project taught us:
- The importance of thorough **design before implementation**.
- Practical use of **UML diagrams** to guide development.
- Effective application of **object-oriented programming** and the MVC pattern.

The result is a functional and engaging **Smart Home Simulation Game**, designed for flexibility and potential extensions.
