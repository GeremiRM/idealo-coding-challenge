// Constants
const GRID_WIDTH = 5;
const GRID_HEIGHT = 5;

// GRID
const renderGrid = () => {
  const grid = document.getElementById("grid");
  for (let i = 0; i < GRID_WIDTH; i++) {
    for (let j = 0; j < GRID_HEIGHT; j++) {
      const gridField = createGridField(i, j);
      grid.append(gridField);
    }
  }
};

const createGridField = (x, y) => {
  const template = document.createElement("template");
  const gridField = `
    <div id=grid-${y}-${x}
      class="border-[1px] border-gray-100 shadow-md relative h-[150px] grid place-items-center">
    </div>
  `;

  template.innerHTML = gridField.trim();

  return template.content.firstChild;
};

// FORM
const onSubmit = (e) => {
  e.preventDefault();
  const inputFields = document.querySelectorAll("input[type='text']");
  const instructions = Array.from(inputFields).map(
    (inputField) => inputField.value
  );

  fetchMovements(instructions);
};

// Fetch robot movements from the backend
const fetchMovements = async (instructions) => {
  // Remove empty input fields
  const trimmedInstructions = instructions.filter((instruction) => instruction);

  try {
    const { data } = await axios.post("/robot", {
      instructions: trimmedInstructions,
      grid: { width: GRID_WIDTH, height: GRID_HEIGHT },
    });

    let robot = data;

    // Remove all inputs
    removeMoveInputFields();

    // Add first input
    addMoveInput();

    // Render the robot
    renderRobot(robot);
  } catch (err) {
    alert("One or more instructions were not written properly. Try again");
  }
};

// Clear all input fields
const removeMoveInputFields = () => {
  const inputFields = document.getElementById("input-fields");
  inputFields.innerHTML = "";
};

// Add new move input field
const addMoveInput = () => {
  const inputFields = document.getElementById("input-fields");
  const numberOfFields = inputFields.children.length;
  const newInputField = createInputElement(
    numberOfFields ? numberOfFields + 1 : 1
  );

  inputFields.append(newInputField);
};

const removeMoveInput = () => {
  const inputFields = document.getElementById("input-fields");
  // if only one child, do nothing
  if (inputFields.children.length === 1) return;

  const lastInput = inputFields.lastChild;
  lastInput.remove();
};

// Input field element
const createInputElement = (fieldNumber) => {
  const template = document.createElement("template");
  const inputField = `
    <div class="mt-4">
      <label for="field-${fieldNumber}">Move ${fieldNumber}</label>
      <input 
        class="appearance-none block w-full bg-white text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none" 
        type="text"
        id="field-${fieldNumber}"
      />
    </div>
  `;

  template.innerHTML = inputField.trim();

  return template.content.firstChild;
};

// ROBOT
const renderRobot = (robot) => {
  deleteRobot();
  const { x, y } = robot.coordinates;

  const grid = document.getElementById(`grid-${x}-${y}`);
  const robotImg = createRobot(robot.direction);
  grid.append(robotImg);
};

const createRobot = (direction) => {
  const robotImg = document.createElement("img");

  switch (direction) {
    case "NORTH":
      rotation = "-rotate-90";
      break;
    case "SOUTH":
      rotation = "rotate-90";
      break;
    case "WEST":
      rotation = "-scale-x-100";
      break;
    case "EAST":
      rotation = "rotate-0";
  }

  robotImg.src = "./imgs/robot.png";
  robotImg.id = "robot";
  robotImg.className = `h-auto max-[90px] min-w-[30px] w-2/6 ${rotation} absolute`;
  return robotImg;
};

const deleteRobot = () => {
  const robotImg = document.getElementById("robot");
  if (robotImg) robotImg.remove();
};

// Add submit function to form
document.getElementById("form").onsubmit = onSubmit;

// Add addInput and removeInput functions to buttons
document.getElementById("add-move").onclick = addMoveInput;
document.getElementById("delete-move").onclick = removeMoveInput;

// Fetch robot initial position
fetchMovements([]);

// Add first move input
addMoveInput();

// Render grid
renderGrid();
