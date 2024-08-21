# Assignment 01 - Lecture 21

## Task 01: Learning HTML and CSS (Optional)

I used to learn basic of HTML and CSS from w3schools

1. [Learning HTML](https://www.w3schools.com/html/) - For learning basic HTML
2. [Learning CSS](https://www.w3schools.com/css/) - For learning basic CSS

## Task 02: Setup env and Install Angular

![Setup Angular](img/Setup%20Env.png)

1. `Node Version 20.16.0`
2. `Package Manager Version npm 10.8.1`
3. `Angular CLI Version 18.2.0`

## Task 03: Run Code and Look Code Structure

Link for this project [Project Demo Angular](https://github.com/helenhash/angular-demo)

### Structure Code

1. Angular Application folder name `angular-demo`
2. Modules that used in this project created by `npm install` which in folder `node_modules`
3. `src` folder used to store the resource files for root-level application project
4. In `app` folder that inside `src` folder contain components, application logic, and data that used to build the project, like `app-component`, `customer-component`, `customer-detail-component`, `customer-module-model`, and `customer-module-service`.

### Running Code

![Run Example Code](img/Run%20Example%20Project%205.png)

![Run Example Code](img/Run%20Example%20Project%206.png)

![Run Example Code](img/Run%20Example%20Project%203.png)

![Run Example Code](img/Run%20Example%20Project%204.png)

![Run Example Code](img/Run%20Example%20Project%201.png)

![Run Example Code](img/Run%20Example%20Project%202.png)

## Task 04: Investigate Component Lifecycle

`Componen Lifecycle` is a sequence of steps that happen between the component's creation and its destruction. These events are managed by Angular and allow developers to hook into specific points in a component's life to perform actions like data fetching, cleanup, or updating the view.

### Main Lifecycle in Angular

1. `constructor` - Runs when Angular instantiates the component.
2. `ngOnInit` - Run when initialized all the component's inputs.
3. `ngOnChanges` - Run when component's inputs have changed.
4. `ngDoCheck` - Run when component is checked for changes.
5. `ngAfterContentInit` - Run when component's content has been initialized.
6. `ngAfterContentChecked` - Run when component content has been checked for changes.
7. `ngAfterViewInit` - Run when component's view has been initialized.
8. `ngAfterViewChecked` - Run when component's view has been checked for changes.
9. `afterNextRender` - Run once when all components have been rendered to the DOM.
10. `afterRender` - Run every time when all components have been rendered to the DOM
11. `ngOnDestroy` - Run when before the component is destroyed.

## Task 05: Compare Between Standalone and No-Standalone App

### No-Standalone App

### Standalone App

## Task 06: Create New Component "Login" in Standalone App