package RealChallenges;

import java.util.Stack;

public class AsteroidsChallenge {

    static class Asteroid {
        int mass;
        int direction;

        Asteroid(int mass, int direction) {
            this.mass = mass;
            this.direction = direction;
        }

        int getMass() {
            return this.mass;
        }

        int getDirection() {
            return this.direction;
        }
    }

    public static int asteroidCollision(Asteroid[] asteroids) {
        Stack<Asteroid> stack = new Stack<>();

        for (Asteroid asteroid : asteroids) {
            int direction = asteroid.getDirection();
            int mass = asteroid.getMass();
            if (direction > 0) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && mass > stack.peek().getMass()) {
                    stack.pop();
                }
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {

        /**
         *    5   3   4   7   1
         *    1   1  -1   1   1
         *    answer 0
         */
        Asteroid[] asteroids1 = new Asteroid[]{
            new Asteroid(5, 1),
            new Asteroid(3, 1),
            new Asteroid(4, 1),
            new Asteroid(7, -1),
            new Asteroid(1, -1)
        };

        /**
         *    8   3   4   7   1
         *    1   1   1  -1  -1
         *    answer 1
         */
        Asteroid[] asteroids2 = new Asteroid[]{
            new Asteroid(8, 1),
            new Asteroid(3, 1),
            new Asteroid(4, 1),
            new Asteroid(7, -1),
            new Asteroid(1, -1)
        };

        /**
         *     2   8    7   7   10
         *    -1   1   -1  -1  -1
         *    answer 0
         */
        Asteroid[] asteroids3 = new Asteroid[]{
            new Asteroid(2, -1),
            new Asteroid(8, 1),
            new Asteroid(7, -1),
            new Asteroid(7, -1),
            new Asteroid(10, -1)
        };

        System.out.println(asteroidCollision(asteroids1) == 0);
        System.out.println(asteroidCollision(asteroids2) == 1);
        System.out.println(asteroidCollision(asteroids3) == 0);

    }
}
