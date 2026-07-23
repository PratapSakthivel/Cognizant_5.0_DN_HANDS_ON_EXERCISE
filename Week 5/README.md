# Week 5: Angular Hands-On Exercises

## HANDS-ON 1: Environment Setup, Project Structure & First Component

### Topics Covered:
- ✅ Angular CLI & Workspace Setup
- ✅ Angular Project Structure & Files
- ✅ Running & Building Angular Apps
- ✅ Creating Components
- ✅ Angular Module Overview

### Project: Student Course Portal

### Setup Instructions:
```bash
npm install -g @angular/cli
ng new student-course-portal --routing --style=css
cd student-course-portal
ng serve
```

### Task 1: Scaffold and Explore the Angular Project

#### Completed Steps:

1. **Project Created**
   - Angular project scaffolded with routing and CSS styling
   - Angular 17+ standalone components configuration

2. **File Explanations (notes.txt)**
   - angular.json: Build configuration
   - tsconfig.json: Root TypeScript configuration
   - tsconfig.app.json: Application-specific TypeScript config
   - package.json: Project dependencies and scripts
   - src/main.ts: Application entry point
   - src/app/app.config.ts: Standalone app configuration
   - src/app/app.ts: Root component
   - src/index.html: Main HTML file

3. **ng serve - Running**
   - Application running on: http://localhost:4200/
   - Watch mode enabled for automatic rebuilds

4. **ng build - Completed**
   - Production build generated successfully
   - Output location: dist/student-course-portal
   - Bundle size: ~213.70 kB (main-*.js)
   - Build time: 23.444 seconds

5. **Budgets Configuration**
   - Location: angular.json > architect > build > configurations > production
   - maximumWarning: Threshold for bundle size warnings
   - maximumError: Threshold for bundle size errors
   - Purpose: Prevents accidentally creating oversized bundles

#### Generated Files in dist/:
- main-*.js: Compiled application code
- styles-*.css: Global CSS styles
- index.html: Production HTML
- favicon.ico: Application icon

### Expected Outcome: ✅ Complete
- ng serve runs without errors
- notes.txt contains explanations for all 8 files
- ng build produces a dist/ folder with compiled code

### Testing:
- Open http://localhost:4200/ in browser
- Verify Angular welcome page loads
- Check dist/ folder for production build

---

**Framework:** Angular 20 (Standalone Components)
**Server:** Development server with HMR
**Build Tool:** Angular CLI
