# Planner Back-end (API) documentation


### User
##### For getting user
* **URL**

  _/api/user/{crededtial}_
* **Method:**
  <code>GET</code>
 
*  **URL Params**
   `credential=[string]` - can be either username or UUID

##### For creating dashboard
* **URL**

  _/api/user/createDashboard/{userid}_
* **Method:**
  `POST`
 
*  **URL Params**
   `userid=[UUID]` - user id
* **Data Params**
  ```
    {
        title: string;
    }
  ```
  
  ##### For joining dashboard
* **URL**

  _/api/user/joinDashboard_
* **Method:**
  `POST`

* **Data Params**
  ```
    {
        userid: UUID;
        dashboardid: UUID;
    }
    or
    {
        username: string;
        dashboardid: UUID;
    }
  ```
  ##### For leaving dashboard
* **URL**

  _/api/user/leaveDashboard/{dashboardid}/{userid}_
* **Method:**
  `GET`
 
*  **URL Params**
   `dashboardid=[UUID]` - dashboard ID,
   `userid=[UUID]` - user ID

  ##### For deleting dashboard
* **URL**

  _/api/user/deleteDashboard/{dashboardid}_
* **Method:**
  `GET`
 
*  **URL Params**
   `dashboardid=[UUID]` - dashboard ID

  ##### For reading all notifications
* **URL**

  _/api/user/readAllNotifications/{userid}_
* **Method:**
  `GET`
 
*  **URL Params**
   `userid=[UUID]` - user ID

##### For clearing notifications
* **URL**

  _/api/user/clearAllNotifications/{userid}_
* **Method:**
  `GET`
 
*  **URL Params**
   `userid=[UUID]` - user ID
  
  

