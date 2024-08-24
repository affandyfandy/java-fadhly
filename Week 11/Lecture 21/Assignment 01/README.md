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

```typescript
constructor() {
    console.log('Constructor called');
}
```

2. `ngOnInit` - Run when initialized all the component's inputs.

```typescript
ngOnInit(): void {
    console.log('ngOnInit called');
}
```

3. `ngOnChanges` - Run when component's inputs have changed.

```typescript
ngOnChanges(changes: SimpleChanges): void {
    console.log('ngOnChanges called', changes);
}
```

4. `ngDoCheck` - Run when component is checked for changes.

```typescript
ngDoCheck(): void {
    console.log('ngDoCheck called');
}
```

5. `ngAfterContentInit` - Run when component's content has been initialized.

```typescript
ngAfterContentInit(): void {
    console.log('ngAfterContentInit called');
}
```

6. `ngAfterContentChecked` - Run when component content has been checked for changes.

```typescript
ngAfterContentChecked(): void {
    console.log('ngAfterContentChecked called');
}
```

7. `ngAfterViewInit` - Run when component's view has been initialized.

```typescript
ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');
}
```

8. `ngAfterViewChecked` - Run when component's view has been checked for changes.

```typescript
ngAfterViewChecked(): void {
    console.log('ngAfterViewChecked called');
}
```

9. `afterNextRender` - Run once when all components have been rendered to the DOM.

```typescript
afterNextRender(): void {
    console.log('afterNextRender custom logic called');
}
```

10. `afterRender` - Run every time when all components have been rendered to the DOM.

```typescript
afterRender(): void {
    console.log('afterRender custom logic called');
}
```

11. `ngOnDestroy` - Run when before the component is destroyed.

```typescript
ngOnDestroy(): void {
    console.log('ngOnDestroy called');
}
```


## Task 05: Compare Between Standalone and No-Standalone App

Main difference Angular that using Standalone and not using Standalone is from declare and using the module or the component or importing the library. In Standalone the should declare `standalone: true` in the `component.ts` to use the other component or module or the library, but in the no Standalone it used `ngModule` in the main app component toimportr component or module or the library also.

### Standalone

![App Componenet Standalone](img/Standalone%201.png)

![Componenet Standalone](img/Standalone%202.png)

### No-Standalone

![App Component Module No Standalone](img/No%20Standalone%201.png)

![App Routing No Standalone](img/No%20Standalone%203.png)

![Component Module No Standalone](img/No%20Standalone%202.png)

## Task 06: Create New Component "Login" in Standalone App

1. `Create Project Angular with Standalone`

```
ng new <name-project> --ssr=false

cd <name-project>

ng serve
```

The project will default in `http://localhost:4200`

2. `Setting Mock DB`

Create `db.json` in `<name-project>/`

```
npm install json-server

npx json-server db.json
```

Json server started in `http://localhost:3000/`

3. `Create Model User to Login`

```
ng generate class <models/class-name>
```

![Setting Model Login](img/Setting%20Model.png)

4. `Create Service to Login`

```
ng generate service <services/service-name>
```

![Setting Service Login](img/Setting%20Service.png)

5. `Create Login Component to Handle UI and Logic`

```
ng generate component <component-name>
```

![Setting Component Login](img/Setting%20Login%20Component%201.png)

![Setting Component Login](img/Setting%20Login%20Component%202.png)

![Setting UI Login](img/Setting%20Login%20UI.png)