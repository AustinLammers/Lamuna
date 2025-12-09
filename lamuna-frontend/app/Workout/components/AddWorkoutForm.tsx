"use client";

type AddWorkoutFormProps = {
  newWorkoutName: string;
  newWorkoutCalories: string;
  newWorkoutType: string;
  newWorkoutMinutes: string;
  newWorkoutSets: string;
  newWorkoutReps: string;
  newWorkoutDescription: string;
  onNameChange: (value: string) => void;
  onCaloriesChange: (value: string) => void;
  onTypeChange: (value: string) => void;
  onMinutesChange: (value: string) => void;
  onSetsChange: (value: string) => void;
  onRepsChange: (value: string) => void;
  onDescriptionChange: (value: string) => void;
  onSubmit: (event: React.FormEvent) => void;
};

const AddWorkoutForm = ({
  newWorkoutName,
  newWorkoutCalories,
  newWorkoutType,
  newWorkoutMinutes,
  newWorkoutSets,
  newWorkoutReps,
  newWorkoutDescription,
  onNameChange,
  onCaloriesChange,
  onTypeChange,
  onMinutesChange,
  onSetsChange,
  onRepsChange,
  onDescriptionChange,
  onSubmit,
}: AddWorkoutFormProps) => {
  return (
    <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4 shadow-sm">
      <div className="mb-3 text-lg font-bold text-gray-900">Add Workout</div>
      <form className="flex flex-col gap-3" onSubmit={onSubmit}>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Workout Name
          </label>
          <input
            type="text"
            required
            value={newWorkoutName}
            onChange={(e) => onNameChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Description
          </label>
          <input
            type="text"
            value={newWorkoutDescription}
            onChange={(e) => onDescriptionChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Calories Burned
          </label>
          <input
            type="number"
            min="0"
            value={newWorkoutCalories}
            onChange={(e) => onCaloriesChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">Type</label>
          <select
            value={newWorkoutType}
            onChange={(e) => onTypeChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          >
            <option value="TIMED">Timed</option>
            <option value="REPS">Reps</option>
          </select>
        </div>
        <div className="grid grid-cols-1 gap-3 md:grid-cols-3">
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">
              Minutes
            </label>
            <input
              type="number"
              min="0"
              value={newWorkoutMinutes}
              onChange={(e) => onMinutesChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="min"
            />
          </div>
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">Sets</label>
            <input
              type="number"
              min="0"
              value={newWorkoutSets}
              onChange={(e) => onSetsChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="sets"
              required
            />
          </div>
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">Reps</label>
            <input
              type="number"
              min="0"
              value={newWorkoutReps}
              onChange={(e) => onRepsChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="reps"
            />
          </div>
        </div>
        <button
          type="submit"
          className="mt-2 rounded bg-teal-700 px-4 py-2 text-sm font-semibold text-white hover:bg-teal-800"
        >
          Add Workout
        </button>
      </form>
    </div>
  );
};

export default AddWorkoutForm;
