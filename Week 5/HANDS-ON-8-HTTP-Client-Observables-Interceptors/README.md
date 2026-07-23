# HANDS-ON 8: HTTP Client — API Integration, Observables & Interceptors

**Digital Nurture 5.0 | Angular (v20.0) | Advanced Level**

## Topics Covered

✓ Setting Up HttpClientModule  
✓ GET, POST, PUT, DELETE with HttpClient  
✓ Observables vs Promises  
✓ RxJS Operators — map, catchError, tap, switchMap  
✓ HTTP Interceptors  
✓ Error Handling & Retry Strategies  

## Overview

**Real Angular applications communicate with backend APIs.** This hands-on replaces all hardcoded service data with live HTTP calls, handles errors gracefully, and adds interceptors for cross-cutting concerns like auth tokens and logging.

## Setup Instructions

### Install JSON Server (Mock Backend)

```bash
npm install -g json-server
```

### Start JSON Server

```bash
json-server --watch db.json --port 3000
```

**Access API at:** http://localhost:3000

**Endpoints:**
- GET http://localhost:3000/courses
- GET http://localhost:3000/courses/1
- POST http://localhost:3000/courses
- PUT http://localhost:3000/courses/1
- DELETE http://localhost:3000/courses/1

## Project Structure

```
HANDS-ON-8-HTTP-Client-Observables-Interceptors/
├── db.json                                # JSON Server database
├── Output/                                # Screenshots
└── README.md                              # This file (comprehensive guide)
```

## Task 1: Replace Service Data with HttpClient Calls

### HttpClient Setup

**For Standalone App (app.config.ts):**
```typescript
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
```

**For Module-based App:**
```typescript
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [HttpClientModule]
})
```

### Refactored CourseService

```typescript
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  // GET all courses
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);
  }

  // GET single course
  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`);
  }

  // POST new course
  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

  // PUT update course
  updateCourse(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, course);
  }

  // DELETE course
  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
```

### Component Subscription

```typescript
ngOnInit(): void {
  this.isLoading = true;
  
  this.courseService.getCourses().subscribe({
    next: (courses) => {
      this.courses = courses;
      console.log('Courses loaded:', courses.length);
    },
    error: (err) => {
      this.errorMessage = err.message;
      console.error('Error loading courses:', err);
    },
    complete: () => {
      this.isLoading = false;
      console.log('Course loading complete');
    }
  });
}
```

### Key Concepts

**Observable vs Promise:**

| Feature | Observable | Promise |
|---------|-----------|---------|
| **Lazy** | Yes (cold) | No (eager) |
| **Cancellable** | Yes | No |
| **Multiple values** | Yes | No (single) |
| **Operators** | Many (RxJS) | Limited |
| **Retry** | Built-in | Manual |

**Cold Observable:**
- HttpClient returns cold Observables
- Does NOT execute until subscribed
- Each subscription creates new HTTP request
- Always remember to unsubscribe or use async pipe

## Task 2: RxJS Operators and Error Handling

### RxJS Operators Chain

```typescript
import { map, catchError, tap, retry } from 'rxjs/operators';
import { throwError } from 'rxjs';

getCourses(): Observable<Course[]> {
  return this.http.get<Course[]>(this.apiUrl).pipe(
    // 1. tap - Side effect (logging)
    tap(courses => console.log('Raw data:', courses.length)),
    
    // 2. map - Transform data
    map(courses => courses.filter(c => c.credits > 0)),
    
    // 3. retry - Retry failed requests
    retry(2),
    
    // 4. catchError - Handle errors
    catchError(err => {
      console.error('HTTP Error:', err);
      return throwError(() => 
        new Error('Failed to load courses. Please try again.')
      );
    })
  );
}
```

### RxJS Operators Explained

#### 1. **map** - Transform Data

```typescript
// Transform response before component receives it
getCourses().pipe(
  map(courses => courses.filter(c => c.credits > 0))
)
```

**Use Case:** Data transformation, filtering, formatting

#### 2. **tap** - Side Effects

```typescript
getCourses().pipe(
  tap(courses => {
    console.log('Courses loaded:', courses.length);
    this.analytics.track('courses_loaded');
  })
)
```

**Why tap instead of map?**
- `tap` doesn't modify the stream
- Used for logging, analytics, debugging
- Never alter data in `tap`
- Use `map` for transformations

#### 3. **catchError** - Error Handling

```typescript
getCourses().pipe(
  catchError(err => {
    console.error(err);
    // Return new Observable or throw
    return throwError(() => new Error('Custom error message'));
  })
)
```

**Patterns:**
```typescript
// Pattern 1: Return error Observable
catchError(err => throwError(() => new Error('...')))

// Pattern 2: Return empty array (recovery)
catchError(() => of([]))

// Pattern 3: Return default value
catchError(() => of(DEFAULT_COURSES))
```

#### 4. **retry** - Retry Strategy

```typescript
getCourses().pipe(
  retry(2),  // Retry up to 2 times
  catchError(err => ...)
)
```

**Advanced retry:**
```typescript
import { retryWhen, delay, take } from 'rxjs/operators';

getCourses().pipe(
  retryWhen(errors =>
    errors.pipe(
      delay(1000),  // Wait 1 second
      take(3)       // Max 3 retries
    )
  )
)
```

#### 5. **switchMap** - Chain HTTP Calls

```typescript
// When course selected, load its students
this.selectedCourseId$.pipe(
  switchMap(courseId => 
    this.enrollmentService.getStudentsByCourse(courseId)
  )
).subscribe(students => this.students = students);
```

**Why switchMap?**
- Cancels previous inner Observable
- Prevents out-of-order responses
- Essential for type-ahead search
- Only latest request matters

**Other Flattening Operators:**
- `switchMap` - Cancel previous, use latest
- `mergeMap` - Keep all, merge results
- `concatMap` - Queue, wait for each to complete
- `exhaustMap` - Ignore new while busy

### Error Handling in Component

```typescript
// Template
<div *ngIf="errorMessage" class="error-banner">
  {{ errorMessage }}
  <button (click)="retry()">Retry</button>
</div>

<div *ngIf="isLoading" class="loading-spinner">
  Loading courses...
</div>

<div *ngIf="!isLoading && !errorMessage">
  <!-- Course list -->
</div>
```

## Task 3: HTTP Interceptors

### Auth Interceptor

**File:** `interceptors/auth.interceptor.ts`

```typescript
import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Clone request and add Authorization header
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer mock-token-12345`
    }
  });

  console.log('Auth Interceptor: Added token to', req.url);
  return next(authReq);
};
```

### Error Handler Interceptor

```typescript
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError(err => {
      if (err.status === 401) {
        console.error('Unauthorized - redirecting to login');
        router.navigate(['/']);
      } else if (err.status === 500) {
        console.error('Server error:', err.message);
        alert('Server error. Please try again later.');
      }
      return throwError(() => err);
    })
  );
};
```

### Loading Interceptor

**LoadingService:**
```typescript
@Injectable({ providedIn: 'root' })
export class LoadingService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  isLoading$ = this.loadingSubject.asObservable();

  show() { this.loadingSubject.next(true); }
  hide() { this.loadingSubject.next(false); }
}
```

**Loading Interceptor:**
```typescript
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { LoadingService } from '../services/loading.service';

export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);
  
  loadingService.show();
  
  return next(req).pipe(
    finalize(() => loadingService.hide())
  );
};
```

**Why finalize?**
- Runs whether Observable completes OR errors
- Equivalent to try/catch/finally
- Perfect for cleanup (hiding spinners)

### Register Interceptors

**app.config.ts:**
```typescript
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './interceptors/auth.interceptor';
import { errorHandlerInterceptor } from './interceptors/error-handler.interceptor';
import { loadingInterceptor } from './interceptors/loading.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withInterceptors([
        authInterceptor,
        errorHandlerInterceptor,
        loadingInterceptor
      ])
    )
  ]
};
```

### Interceptor Order

**Request Flow:**
```
Component → Auth → ErrorHandler → Loading → HTTP Request
```

**Response Flow:**
```
HTTP Response → Loading → ErrorHandler → Auth → Component
```

**Key Points:**
- Interceptors run in registration order (request)
- Response travels back in reverse order
- Order matters for auth + error handling
- Each interceptor must call `next(req)`

## Features Implemented

### ✅ HTTP Operations:
- [x] GET requests for course list
- [x] GET single course by ID
- [x] POST to create new course
- [x] PUT to update course
- [x] DELETE to remove course

### ✅ RxJS Operators:
- [x] map - Data transformation
- [x] tap - Logging side effects
- [x] catchError - Error handling
- [x] retry - Automatic retry
- [x] switchMap - Chain HTTP calls
- [x] finalize - Cleanup operations

### ✅ Interceptors:
- [x] Auth interceptor (add token)
- [x] Error handler interceptor (401/500)
- [x] Loading interceptor (global spinner)

### ✅ Error Handling:
- [x] Try/catch pattern
- [x] Error messages in UI
- [x] Retry strategy
- [x] Global error handling

## Running the Application

### 1. Start JSON Server

```bash
cd HANDS-ON-8-HTTP-Client-Observables-Interceptors
json-server --watch db.json --port 3000
```

### 2. Start Angular App

```bash
cd student-course-portal-8
npm install
npm start
```

### 3. Verify API

Open http://localhost:3000/courses in browser

## Testing the Application

### Test Case 1: GET Request
1. Open http://localhost:4200/courses
2. Open DevTools → Network tab
3. See GET request to localhost:3000/courses
4. ✅ Courses load from API

### Test Case 2: POST Request
1. Fill enrollment form
2. Submit
3. Check Network tab → POST request
4. Check db.json file → new course added
5. ✅ Data persisted

### Test Case 3: Error Handling
1. Stop JSON Server
2. Refresh courses page
3. ✅ Error message appears
4. ✅ Retry button works
5. Start JSON Server
6. Click Retry
7. ✅ Courses load

### Test Case 4: Auth Interceptor
1. Open DevTools → Network
2. Click any API request
3. Check Request Headers
4. ✅ See `Authorization: Bearer mock-token-12345`

### Test Case 5: Loading Interceptor
1. Navigate to Courses
2. ✅ Loading spinner appears
3. When loaded
4. ✅ Spinner disappears

### Test Case 6: switchMap
1. Quickly click different courses
2. ✅ Only latest request completes
3. Previous requests cancelled

## Observables Best Practices

### ✅ DO:
- Use async pipe in templates
- Unsubscribe in ngOnDestroy if manual subscription
- Use RxJS operators for transformations
- Handle errors with catchError
- Use tap for side effects only
- Chain operations with pipe

### ❌ DON'T:
- Forget to subscribe (cold Observables)
- Subscribe multiple times unnecessarily
- Modify data in tap operator
- Forget to unsubscribe (memory leaks)
- Use nested subscriptions (use switchMap)
- Mix Promises and Observables without conversion

## Common RxJS Patterns

### 1. Type-Ahead Search

```typescript
searchTerm$.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(term => this.searchService.search(term))
).subscribe(results => this.results = results);
```

### 2. Dependent HTTP Calls

```typescript
getUser(id).pipe(
  switchMap(user => getOrders(user.id)),
  switchMap(orders => getOrderDetails(orders[0].id))
).subscribe(details => ...);
```

### 3. Parallel HTTP Calls

```typescript
forkJoin({
  courses: this.courseService.getCourses(),
  students: this.studentService.getStudents()
}).subscribe(({ courses, students }) => ...);
```

### 4. Polling

```typescript
interval(5000).pipe(
  switchMap(() => this.dataService.getData())
).subscribe(data => this.data = data);
```

## Async Pipe Benefits

**Instead of:**
```typescript
courses: Course[] = [];
ngOnInit() {
  this.courseService.getCourses().subscribe(
    courses => this.courses = courses
  );
}
```

**Use:**
```typescript
courses$ = this.courseService.getCourses();

// Template: {{ courses$ | async }}
```

**Benefits:**
- ✅ Auto subscribe
- ✅ Auto unsubscribe
- ✅ No memory leaks
- ✅ Cleaner code
- ✅ Better performance

## Troubleshooting

### CORS Error
**Problem:** Access-Control-Allow-Origin error
**Solution:** JSON Server allows CORS by default, but add `--cors` flag if needed

### 404 Not Found
**Problem:** API endpoint not found
**Solution:** Check JSON Server is running on port 3000

### Observable not executing
**Problem:** HTTP call doesn't run
**Solution:** Remember to subscribe! Observables are cold.

### Memory Leaks
**Problem:** Component destroyed but subscriptions active
**Solution:** Use async pipe or unsubscribe in ngOnDestroy

## Technologies Used

- **Angular**: v21.2.0
- **RxJS**: v7.8.0
- **HttpClient**: @angular/common/http
- **JSON Server**: v0.17.0+

## Learning Outcomes

After completing this hands-on exercise, you should be able to:

✅ Set up HttpClient in Angular  
✅ Make GET, POST, PUT, DELETE requests  
✅ Understand Observables vs Promises  
✅ Use RxJS operators (map, tap, catchError, switchMap)  
✅ Implement retry strategies  
✅ Create HTTP interceptors  
✅ Handle errors gracefully  
✅ Use async pipe for automatic subscription  
✅ Chain HTTP calls with switchMap  
✅ Implement global loading indicators  

## Next Steps

Consider enhancing with:
- Pagination with HTTP params
- Caching strategies
- Optimistic UI updates
- WebSocket integration
- GraphQL with Apollo
- State management (NgRx)
- Real authentication flow
- Request cancellation

---

**Completed**: July 23, 2026  
**Digital Nurture 5.0 Program**  
**Angular Version**: 21.2.0

## Quick Reference

### HTTP Methods
```typescript
http.get<T>(url)
http.post<T>(url, body)
http.put<T>(url, body)
http.delete<T>(url)
http.patch<T>(url, body)
```

### Common Operators
```typescript
map()        // Transform data
tap()        // Side effects
catchError() // Error handling
retry()      // Retry failed requests
switchMap()  // Chain, cancel previous
mergeMap()   // Chain, keep all
finalize()   // Cleanup (always runs)
```

### Interceptor Template
```typescript
export const myInterceptor: HttpInterceptorFn = (req, next) => {
  // Modify request
  const modifiedReq = req.clone({ ... });
  
  // Continue chain
  return next(modifiedReq).pipe(
    // Modify response
    map(event => ...),
    catchError(err => ...)
  );
};
```
