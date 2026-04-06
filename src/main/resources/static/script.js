const API_URL = '/api/habits';

document.addEventListener('DOMContentLoaded', fetchHabits);

// Bind Enter key to input
const inputField = document.getElementById('habitName');
inputField.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        addHabit();
    }
});

async function fetchHabits() {
    try {
        const response = await fetch(API_URL);
        const habitsObject = await response.json();
        await renderHabits(habitsObject);
    } catch (error) {
        console.error('Error fetching habits:', error);
    }
}

async function addHabit() {
    const input = document.getElementById('habitName');
    const name = input.value.trim();
    if (!name) return;

    const btnBtn = document.getElementById('addBtn');
    btnBtn.disabled = true;

    try {
        const response = await fetch(`${API_URL}?name=${encodeURIComponent(name)}`, {
            method: 'POST'
        });
        if (response.ok) {
            input.value = '';
            await fetchHabits();
        } else {
            alert('Adding habit failed. It might already exist.');
        }
    } catch (error) {
        console.error('Error adding habit:', error);
    } finally {
        btnBtn.disabled = false;
    }
}

async function markDone(name) {
    try {
        const response = await fetch(`${API_URL}/${encodeURIComponent(name)}/complete`, {
            method: 'POST'
        });
        if (response.ok) {
            await fetchHabits(); // refresh 
        } else {
            console.error('Failed to mark as done');
        }
    } catch (error) {
        console.error('Error marking habit done:', error);
    }
}

// Ensure the dates align with Server timezone for UI checking.
// The backend uses java.time.LocalDate.now(). We can just check last date locally or wait for backend.
// In this basic version we just allow clicking markDone anytime.

async function renderHabits(habitsObject) {
    const list = document.getElementById('habitsList');
    list.innerHTML = ''; 

    const names = Object.keys(habitsObject);

    // Fetch streaks concurrently
    const streakPromises = names.map(async (name) => {
        try {
            const res = await fetch(`${API_URL}/${encodeURIComponent(name)}/streak`);
            const val = await res.json();
            return { name, streak: val };
        } catch (e) {
            return { name, streak: 0 };
        }
    });

    const streaks = await Promise.all(streakPromises);
    const streakMap = {};
    streaks.forEach(s => { streakMap[s.name] = s.streak; });

    names.forEach(name => {
        const habitData = habitsObject[name];
        const card = document.createElement('div');
        card.className = 'habit-card';
        
        let isDoneToday = false;
        if (habitData.completedDates && habitData.completedDates.length > 0) {
            const today = new Date().toISOString().split('T')[0];
            const lastDate = habitData.completedDates[habitData.completedDates.length - 1];
            if (lastDate === today) {
                isDoneToday = true;
            }
        }

        const streakVal = streakMap[name] || 0;

        card.innerHTML = `
            <div class="habit-info">
                <h3>${name}</h3>
                <span class="streak-badge">🔥 ${streakVal} Day Streak</span>
            </div>
            <button class="${isDoneToday ? 'btn-complete done' : 'btn-complete'}" 
                    onclick="${isDoneToday ? '' : `markDone('${name.replace(/'/g, "\\'")}')`}">
                ${isDoneToday ? '✓ Done' : 'Mark Done'}
            </button>
        `;
        list.appendChild(card);
    });
}
