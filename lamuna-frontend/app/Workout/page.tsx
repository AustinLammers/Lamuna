"use client";

import axios from "axios";
import { useEffect, useState } from "react";
import DateSwitcher from "./components/DateSwitcher";
import AddWorkoutForm from "./components/AddWorkoutForm";

type WorkoutItem = {
  id: number;
  name: string;
  description?: string;
  date?: string;
  type?: string;
  calories?: number;
  minutes?: number;
  sets?: number;
  reps?: number;
};

const Workout = () => {
  const todayString = new Date().toISOString().slice(0, 10);
  const [selectedDate, setSelectedDate] = useState<string>(todayString);
  const [workouts, setWorkouts] = useState<WorkoutItem[]>([]);
  const [selectedWorkout, setSelectedWorkout] = useState<WorkoutItem | null>(
    null,
  );
  const [newWorkoutName, setNewWorkoutName] = useState<string>("");
  const [newWorkoutCalories, setNewWorkoutCalories] = useState<string>("");
  const [newWorkoutType, setNewWorkoutType] = useState<string>("TIMED");
  const [newWorkoutMinutes, setNewWorkoutMinutes] = useState<string>("");
  const [newWorkoutSets, setNewWorkoutSets] = useState<string>("");
  const [newWorkoutReps, setNewWorkoutReps] = useState<string>("");
  const [newWorkoutDescription, setNewWorkoutDescription] =
    useState<string>("");

  const formatDate = (date: Date) => {
    return date.toISOString().slice(0, 10);
  };

  const goToPreviousDay = () => {
    const current = new Date(selectedDate);
    current.setDate(current.getDate() - 1);
    setSelectedDate(formatDate(current));
  };

  const goToNextDay = () => {
    const current = new Date(selectedDate);
    current.setDate(current.getDate() + 1);
    const nextDay = formatDate(current);

    if (nextDay > todayString) {
      return;
    }

    setSelectedDate(nextDay);
  };

  useEffect(() => {
    loadWorkouts(selectedDate);
  }, [selectedDate]);

  const loadWorkouts = async (dateString: string) => {
    const response = await axios.get<WorkoutItem[]>(
      "http://localhost:8080/api/workouts",
      {
        params: { date: dateString },
      },
    );

    setWorkouts(response.data || []);
  };

  const handleAddWorkout = async (event: React.FormEvent) => {
    event.preventDefault();

    const caloriesNumber = parseInt(newWorkoutCalories || "0", 10) || 0;
    const minutesNumber = parseInt(newWorkoutMinutes || "0", 10) || 0;
    const setsNumber = parseInt(newWorkoutSets || "0", 10) || 0;
    const repsNumber = parseInt(newWorkoutReps || "0", 10) || 0;

    const hasMinutes = newWorkoutMinutes.trim() !== "";
    const hasReps = newWorkoutReps.trim() !== "";

    if ((hasMinutes && hasReps) || (!hasMinutes && !hasReps)) {
      return;
    }

    await axios.post("http://localhost:8080/api/workouts", {
      name: newWorkoutName,
      description: newWorkoutDescription,
      date: selectedDate,
      calories: caloriesNumber,
      minutes: minutesNumber,
      sets: setsNumber,
      reps: repsNumber,
      type: newWorkoutType,
      userId: 1,
    });

    setNewWorkoutName("");
    setNewWorkoutCalories("");
    setNewWorkoutMinutes("");
    setNewWorkoutSets("");
    setNewWorkoutReps("");
    setNewWorkoutDescription("");

    await loadWorkouts(selectedDate);
  };

  const timedWorkouts = workouts.filter(
    (item) => item.type && item.type.toLowerCase() === "timed",
  );
  const repsWorkouts = workouts.filter(
    (item) => item.type && item.type.toLowerCase() === "reps",
  );

  return (
    <div className="flex flex-col items-center min-h-screen bg-white">
      <div className="flex w-full max-w-4xl flex-col gap-y-6 px-6 py-10">
        <DateSwitcher
          selectedDate={selectedDate}
          todayString={todayString}
          onPrev={goToPreviousDay}
          onNext={goToNextDay}
        />
        <div className="w-full rounded-lg border border-gray-200 bg-white p-4 shadow-sm">
          <div className="mb-3 text-lg font-bold text-gray-900 text-center">
            Workouts for{" "}
            {new Date(selectedDate).toLocaleDateString(undefined, {
              year: "numeric",
              month: "long",
              day: "numeric",
              timeZone: "UTC",
            })}
          </div>
          <div className="grid grid-cols-1 gap-4 md:grid-cols-2">
            <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-gray-200 px-4 py-2 text-sm font-semibold text-gray-900">
                Timed Workouts
              </div>
              <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                <div className="grid grid-cols-3 gap-4 font-semibold text-gray-900">
                  <span>Name</span>
                  <span className="text-right">Calories</span>
                  <span className="text-right">Minutes</span>
                </div>
                {timedWorkouts.map((item, index) => (
                  <button
                    key={`${item.name}-${index}`}
                    className="grid w-full grid-cols-3 gap-4 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedWorkout(item)}
                  >
                    <span className="truncate">{item.name}</span>
                    <span className="text-right">{item.calories || 0}</span>
                    <span className="text-right">{item.minutes || 0}</span>
                  </button>
                ))}
              </div>
            </div>
              <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-gray-200 px-4 py-2 text-sm font-semibold text-gray-900">
                Reps Workouts
              </div>
                <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                  <div className="grid grid-cols-4 gap-2 font-semibold text-gray-900">
                    <span>Name</span>
                    <span className="text-right">Calories</span>
                    <span className="text-right">Sets</span>
                    <span className="text-right">Reps</span>
                  </div>
                  {repsWorkouts.map((item, index) => (
                    <button
                      key={`${item.name}-${index}`}
                      className="grid w-full grid-cols-4 gap-2 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedWorkout(item)}
                    >
                      <span className="truncate">{item.name}</span>
                      <span className="text-right">{item.calories || 0}</span>
                      <span className="text-right">{item.sets || 0}</span>
                      <span className="text-right">{item.reps || 0}</span>
                    </button>
                  ))}
                </div>
            </div>
          </div>
          <AddWorkoutForm
            newWorkoutName={newWorkoutName}
            newWorkoutCalories={newWorkoutCalories}
            newWorkoutType={newWorkoutType}
            newWorkoutMinutes={newWorkoutMinutes}
            newWorkoutSets={newWorkoutSets}
            newWorkoutReps={newWorkoutReps}
            newWorkoutDescription={newWorkoutDescription}
            onNameChange={setNewWorkoutName}
            onCaloriesChange={setNewWorkoutCalories}
            onTypeChange={setNewWorkoutType}
            onMinutesChange={setNewWorkoutMinutes}
            onSetsChange={setNewWorkoutSets}
            onRepsChange={setNewWorkoutReps}
            onDescriptionChange={setNewWorkoutDescription}
            onSubmit={handleAddWorkout}
          />
        </div>
      </div>
      {selectedWorkout ? (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 px-4">
          <div className="w-full max-w-md rounded-lg bg-white p-5 shadow-lg space-y-3">
            <div className="flex items-center justify-between">
              <div className="text-lg font-bold text-gray-900">
                {selectedWorkout.name}
              </div>
              <div className="flex items-center gap-2">
                <button
                  type="button"
                  onClick={async () => {
                    await axios.delete(
                      `http://localhost:8080/api/workouts/${selectedWorkout?.id}`,
                    );
                    setSelectedWorkout(null);
                    loadWorkouts(selectedDate);
                  }}
                  className="rounded bg-red-600 px-3 py-1 text-xs font-semibold text-white hover:bg-red-700"
                >
                  Delete
                </button>
                <button
                  type="button"
                  className="text-sm font-semibold text-gray-700 hover:underline"
                  onClick={() => setSelectedWorkout(null)}
                >
                  Close
                </button>
              </div>
            </div>
            {selectedWorkout.description ? (
              <div className="text-sm text-gray-800">
                {selectedWorkout.description}
              </div>
            ) : null}
            <div className="space-y-2 text-sm text-gray-800">
              <div className="flex justify-between">
                <span>Calories</span>
                <span>{selectedWorkout.calories || 0}</span>
              </div>
              <div className="flex justify-between">
                <span>Minutes</span>
                <span>{selectedWorkout.minutes || 0}</span>
              </div>
              <div className="flex justify-between">
                <span>Sets</span>
                <span>{selectedWorkout.sets || 0}</span>
              </div>
              <div className="flex justify-between">
                <span>Reps</span>
                <span>{selectedWorkout.reps || 0}</span>
              </div>
            </div>
          </div>
        </div>
      ) : null}
    </div>
  );
};

export default Workout;
