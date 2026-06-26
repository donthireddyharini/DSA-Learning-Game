# 🎮 DSA LEGENDS — World of Algorithms

A **cyber-fantasy RPG that teaches Data Structures & Algorithms through gameplay** — not quizzes, lectures, or PDFs. You start as a *DSA Apprentice* and battle your way through seven worlds to become a *DSA Legend*.

This package contains two things:

1. **`index.html`** — the fully playable game (runs instantly in any browser, no install, no build step).
2. **`src/dsa-java/`** — clean, compilable **Java reference implementations** of every algorithm the game teaches (your "DSA in code" layer).

---

## ▶ How to play (30 seconds)

Just **double-click `index.html`** — it opens in your browser and runs. Nothing to install.

- Forge a hero → land on the **Hub** (AAA dashboard with XP, coins, rank, completion %).
- Open **Start Adventure** → the glowing **World Map**.
- Inside a world, the loop is: **📚 Roadmap → 🎮 Missions → 🐉 Boss → 🧠 Quiz.** Finishing all four conquers the world, unlocks its **Power**, and opens the next world.
- Progress **auto-saves** to your browser (localStorage). Use **Continue Journey** to resume; **Profile → Reset** to start fresh.

> Best viewed on desktop/laptop. Works on mobile too (responsive). Respects "reduce motion" OS settings.

---

## ▶ How to run the Java algorithms

The Java layer is the actual DSA, world by world (arrays, linked lists, stacks, queues, trees, graphs).

```bash
cd src/dsa-java
javac dsa-java *.java        # if your files keep the `package dsa;` line, see note below
```

The sources use `package dsa;`. The simplest way to compile & run:

```bash
# from inside src/dsa-java
mkdir -p ../build/dsa
javac -d ../build *.java     # compiles all into ../build/dsa/
cd ../build
java dsa.Main                # runs the full demo across all 6 worlds
```

Requires **JDK 9 or newer** (uses `List.of`, lambdas, generics — all standard library). All sources were **compiled with `javac` and run successfully** (`java dsa.Main` prints a full demo across all 7 worlds). If your setup dislikes the package, just remove the `package dsa;` line from each file and run `javac *.java && java Main`.

Expected output is a guided tour: array insert/delete/search/sort, list reversal, postfix evaluation, priority dispatch, BST inorder + heap drain, and BFS/DFS/Dijkstra on a small graph.

---

## 🗺️ The seven worlds

| # | World | Teaches | Boss | Power unlocked |
|---|-------|---------|------|----------------|
| 1 | 💎 Array Kingdom | indexing, traversal, insert/delete, linear & binary search, 4 sorts | 🐉 Array Dragon | 🔍 Index Vision |
| 2 | 🌲 Linked List Forest | node, pointer, traverse, insert/delete, reverse, cycle detection | 👹 Cycle Monster | ➡️ Pointer Vision |
| 3 | 🌋 Stack Volcano | push/pop/peek, overflow/underflow, postfix eval | 👺 Expression Titan | ↩️ Undo Move |
| 4 | 🏙️ Queue City | FIFO, enqueue/dequeue, circular & priority queue | 🤖 Scheduling Robot | ⏸️ Time Freeze |
| 5 | 🌳 Tree Kingdom | binary tree, BST, traversals, AVL, heap | 🧌 Tree Guardian | 🌿 Traversal Hint |
| 6 | 🌌 Graph Galaxy | nodes/edges, BFS, DFS, shortest path, Dijkstra | 👾 Graph Overlord | 🧭 Path Finder |
| 7 | ⚔️ World War Arena | mixed mastery of all six | 👑 Algorithm Emperor | 🏆 DSA Legend title |

---

## 🎮 What's interactive (honest scope)

This is one self-contained deliverable, so depth is concentrated where it matters most. Everything below is **playable right now**:

**Fully built**
- Boot sequence, hero creation, persistent save/continue, particle background.
- Hub dashboard, ranks (Beginner → DSA Legend), XP/level economy, coins, completion ring.
- 3D-style world map with lock logic, "complete previous world to unlock" gates, and unlock animations.
- **World 1 — Array Kingdom in full:** 7 distinct hands-on missions — Crystal Collection (indexing), Traversal, Treasure Insertion (animated shift), Corrupted Removal (animated shift), Search Dungeon (linear), Binary Search Temple (visual halving), Sorting Arena (bubble/selection/merge/quick as animated weapons).
- **Boss fights** for every world (interactive, timed, HP bars, damage on wrong/timeout).
- **Final quizzes** — scenario-based, fresh randomized values each attempt, 30s/question timer, 70% to pass.
- **Worlds 2–6** signature mini-games (stack falling stones, BST growth, FIFO traffic) + concept trials + bosses + quizzes.
- **World War Arena** with the Algorithm Emperor (mixed questions from all worlds).
- Powers (unlock per world), Inventory/loot, ~24 achievements with toast popups, leaderboard (you vs. seeded bots), learning journal, profile.

**Roadmap (designed, ready to extend)**
- AI-generated quiz questions via the **Anthropic API** (the game currently uses a rich pseudo-dynamic local question engine; swapping in a live `/v1/messages` call is a drop-in upgrade).
- Full multiplayer leaderboards (Friends/World boards) via the Node + MongoDB backend below.
- 100+ achievements (the framework scales — just add rows to the `ACHV` table).
- Bespoke mini-games for every World 2–6 topic (the engine and theming are in place).

---

## 🏗️ Production architecture (from the spec)

The single-file build maps cleanly onto the requested full stack when you scale it up:

```
Frontend  React + TypeScript · Tailwind · Framer Motion · Phaser.js (game scenes)
Backend   Node.js · Express · MongoDB · Firebase Auth
Services  Leaderboard API · Quiz Engine API · AI Question Generator (Anthropic API)
```

**Suggested folder structure**
```
dsa-legends/
├─ client/                 # React + TS + Phaser
│  ├─ src/scenes/          # WorldMapScene, ArrayScene, BossScene, ...
│  ├─ src/components/      # HUD, WorldCard, AchievementToast, LootModal
│  ├─ src/game/            # state, xp economy, achievement engine
│  └─ src/api/             # leaderboard, quiz, auth clients
├─ server/                 # Node + Express
│  ├─ routes/              # /players /worlds /leaderboard /quiz
│  ├─ models/              # Player, World, QuizResult, Achievement
│  └─ services/            # quizGenerator (Anthropic), ranking
└─ src/dsa-java/           # the algorithm reference (this package)
```

**Core MongoDB collections**
- `players` — `{ name, avatar, level, xp, coins, rank, worlds{}, powers[], achievements[], stats{} }`
- `worlds` — static world/roadmap/mission/boss definitions
- `quizResults` — `{ playerId, worldId, score, timeMs, difficulty, createdAt }`
- `leaderboard` — materialized rankings by XP / quiz score / completion speed
- `achievements` — catalog + per-player unlock timestamps

**XP economy (as implemented in the game)**
- Lesson +15 XP · Mission +60–120 XP · Boss +150 XP · Quiz pass +120 XP · World clear +100 XP
- Level threshold: `level × 120` XP. Rank advances with each world conquered.

---

## 🎨 Design language

Cyber-fantasy: void-black canvas, neon-blue / electric-purple / cyber-green glow, glassmorphic floating panels, particle field, and a glowing **energy-path world map** as the signature element. Display type *Orbitron*, body *Rajdhani*, data *JetBrains Mono* (loaded from Google Fonts with safe system fallbacks for offline use).

---

## 📁 Files

```
dsa-legends/
├─ index.html                 ← the playable game (open this)
├─ README.md                  ← you are here
└─ src/dsa-java/
   ├─ ArrayKingdom.java       ← World 1 algorithms
   ├─ LinkedListForest.java   ← World 2
   ├─ StackVolcano.java       ← World 3
   ├─ QueueCity.java          ← World 4
   ├─ TreeKingdom.java        ← World 5
   ├─ GraphGalaxy.java        ← World 6
   └─ Main.java               ← runnable demo of all of them
```

Built as a hackathon-ready foundation. Conquer the Array Kingdom first. 🐉
Project link:https://dsa-legends.vercel.app/
