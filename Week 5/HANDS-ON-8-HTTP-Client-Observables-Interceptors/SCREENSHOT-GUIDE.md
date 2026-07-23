# HANDS-ON 8: Screenshot Guide

## 📸 Complete Screenshot Checklist

### Setup Required:
1. Install JSON Server: `npm install -g json-server`
2. Start JSON Server: `json-server --watch db.json --port 3000`
3. Open any Angular app or use Postman

---

## Screenshot 1: JSON Server Running ✓

**What to capture:**
- Terminal/Command Prompt showing JSON Server output
- Should display:
  ```
  Resources
  http://localhost:3000/courses
  http://localhost:3000/students
  http://localhost:3000/enrollments

  Home
  http://localhost:3000
  ```

**How to take:**
1. Open terminal
2. Navigate to HANDS-ON-8 folder
3. Run: `json-server --watch db.json --port 3000`
4. Screenshot the terminal output

**Save as:** `01-json-server-running.png`

---

## Screenshot 2: API Response in Browser ✓

**What to capture:**
- Browser showing JSON data from API
- URL: http://localhost:3000/courses

**How to take:**
1. Open browser
2. Navigate to: http://localhost:3000/courses
3. You'll see JSON array of courses
4. Screenshot the entire browser window

**Save as:** `02-api-response-browser.png`

---

## Screenshot 3: Postman GET Request ✓

**What to capture:**
- Postman showing successful GET request
- Headers, Response body, Status code

**How to take:**
1. Open Postman
2. Create GET request: http://localhost:3000/courses
3. Click Send
4. Screenshot showing:
   - Status: 200 OK
   - Response body with course data

**Save as:** `03-postman-get-request.png`

---

## Screenshot 4: Postman POST Request ✓

**What to capture:**
- Creating a new course via POST

**How to take:**
1. In Postman, create POST request: http://localhost:3000/courses
2. Set Headers: `Content-Type: application/json`
3. Set Body (raw JSON):
   ```json
   {
     "name": "Advanced Angular",
     "code": "ANG401",
     "credits": 4,
     "gradeStatus": "pending"
   }
   ```
4. Click Send
5. Screenshot showing:
   - Status: 201 Created
   - Response with new course (includes auto-generated ID)

**Save as:** `04-postman-post-request.png`

---

## Screenshot 5: db.json File Updated ✓

**What to capture:**
- The db.json file showing the newly added course

**How to take:**
1. Open db.json in VS Code
2. Scroll to courses array
3. Show the newly added course from POST request
4. Screenshot the file

**Save as:** `05-db-json-updated.png`

---

## Screenshot 6: Postman DELETE Request ✓

**What to capture:**
- Deleting a course

**How to take:**
1. In Postman, create DELETE request: http://localhost:3000/courses/6
2. Click Send
3. Screenshot showing:
   - Status: 200 OK
   - Empty response body

**Save as:** `06-postman-delete-request.png`

---

## Screenshot 7: Network Tab - DevTools ✓

**What to capture:**
- Chrome DevTools Network tab showing HTTP requests

**How to take:**
1. Open http://localhost:3000/courses in Chrome
2. Press F12 to open DevTools
3. Go to Network tab
4. Refresh the page
5. Click on the request to "courses"
6. Screenshot showing:
   - Request URL
   - Status Code: 200
   - Response data

**Save as:** `07-network-tab-devtools.png`

---

## Screenshot 8: Request Headers in DevTools ✓

**What to capture:**
- Headers section showing request/response headers

**How to take:**
1. In DevTools Network tab
2. Click on "courses" request
3. Go to "Headers" tab
4. Screenshot showing:
   - Request Headers section
   - Response Headers section
   - General section (Status, Method, URL)

**Save as:** `08-request-headers-devtools.png`

---

## Screenshot 9: Console Logs (If using Angular app) ✓

**What to capture:**
- Console showing RxJS operator logs

**What to show:**
```
Raw data: 5 courses
Courses loaded: 5
Auth Interceptor: Added token to http://localhost:3000/courses
```

**How to take:**
1. If you have Angular app with console.log in operators
2. Open Console tab in DevTools
3. Navigate to courses page
4. Screenshot console output

**Save as:** `09-console-logs.png`

---

## Screenshot 10: Error Handling ✓

**What to capture:**
- What happens when server is offline

**How to take:**
1. Stop JSON Server (Ctrl+C in terminal)
2. Try to access http://localhost:3000/courses
3. Screenshot showing:
   - Browser error message
   - Or Network tab showing failed request (red)

**Save as:** `10-error-server-offline.png`

---

## 🎯 Simplified Screenshot List

If you want to keep it simple, take these **5 essential screenshots**:

### Essential #1: JSON Server Running
- Terminal showing JSON Server startup

### Essential #2: GET Request (Postman or Browser)
- Successful data retrieval

### Essential #3: POST Request (Postman)
- Creating new course

### Essential #4: db.json File
- Show the data structure

### Essential #5: Network Tab (DevTools)
- Show HTTP request/response

---

## 🚀 Quick Commands Reference

```bash
# Install JSON Server (one time)
npm install -g json-server

# Start JSON Server
cd "Week 5/HANDS-ON-8-HTTP-Client-Observables-Interceptors"
json-server --watch db.json --port 3000

# Test API endpoints
# GET:    http://localhost:3000/courses
# POST:   http://localhost:3000/courses (with JSON body)
# PUT:    http://localhost:3000/courses/1 (with JSON body)
# DELETE: http://localhost:3000/courses/1
```

---

## 📝 Alternative: Use Online Screenshots

If you prefer, I can provide URLs to demonstrate:

1. **JSONPlaceholder** - Public test API
2. **Mockable.io** - Create temporary API
3. **ReqRes.in** - Ready-made test API

Let me know which approach you prefer!

---

## 💡 Tips for Good Screenshots

1. **Use high resolution** - Make text readable
2. **Show full window** - Include URL bar
3. **Highlight important parts** - Use red box or arrow
4. **Keep it clean** - Close unnecessary tabs
5. **Show timestamps** - Proves real-time execution

---

## ✅ Checklist

- [ ] JSON Server running (terminal)
- [ ] Browser showing API data
- [ ] Postman GET request
- [ ] Postman POST request
- [ ] db.json file with new data
- [ ] Postman DELETE request
- [ ] DevTools Network tab
- [ ] Request/Response headers
- [ ] Console logs (optional)
- [ ] Error handling (optional)

---

**Total Screenshots Needed:** 5-10 (depending on depth)

**Estimated Time:** 15-20 minutes

**Tools Needed:**
- Command Prompt / Terminal
- Browser (Chrome)
- Postman (optional but recommended)
- Text Editor (VS Code)
