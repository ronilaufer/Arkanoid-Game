# Arkanoid Game

A classic Arkanoid (Breakout) game written in Java. This project was developed as part of an Object-Oriented Programming course, focusing on clean code architecture, design patterns, and physics-based collision detection.

The game is built using the `biuoop` library, which provides the graphical user interface, drawing surface, and keyboard sensor inputs.

## 🎮 Features
- **Dynamic Physics & Collision Detection**: Custom-built math algorithms for line-to-rectangle intersections to determine precise bounce angles and collision coordinates.
- **Observer Pattern Integration**: Decoupled game rules (score tracking, block removal, and ball management) from the physics engine using standard listeners.
- **Interactive Controls**: Move the paddle using the Left and Right arrow keys to keep the balls in play.
- **Score Tracking**: Visual score indicator tracking hits and block destructions.
- **Smooth Game Loop**: Consistent frame rates and smooth animation.

## 🛠️ Architecture & Design Patterns
- **Observer Pattern**: 
  - `HitNotifier` and `HitListener` interfaces.
  - `BlockRemover` and `BallRemover` listen for hits to remove objects from the game when they are destroyed or fall out of bounds.
  - `ScoreTrackingListener` updates the score count when blocks are hit.
- **Sprites & Collidables**:
  - `Sprite` interface for all drawable elements (Ball, Block, Paddle, ScoreIndicator).
  - `Collidable` interface for elements that can be collided with (Block, Paddle).
  - Managers like `SpriteCollection` and `GameEnvironment` to group, update, and draw objects.
- **Geometry & Math**:
  - Custom implementations of basic geometric shapes (`Point`, `Line`, `Rectangle`) and collision math.

## 🚀 How to Run

### Prerequisites
Make sure you have Java JDK and Apache Ant installed on your system.

### Build and Run with Ant
You can use the provided `build.xml` file to easily compile and run the project:

1. **Compile the project**:
   ```bash
   ant compile
   ```
2. **Run the game**:
   ```bash
   ant run
   ```
3. **Clean build files**:
   ```bash
   ant clean
   ```

### Run directly with Java
If you do not have Ant, you can compile and run using the `biuoop-1.4.jar` dependency:

```bash
# Create bin directory
mkdir bin

# Compile
javac -cp biuoop-1.4.jar -d bin src/**/*.java src/*.java

# Run
java -cp bin;biuoop-1.4.jar Ass5Game
```
*(Note: Use `:` instead of `;` in the classpath on Linux/macOS)*
