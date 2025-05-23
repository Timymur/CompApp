let reports = window.reports || [];

 let labels = reports.map(r => r.id); 
    let coolant_temp = reports.map(r => r.coolant_temp);
    let dew_point = reports.map(r => r.dew_point);
    let gas_pollution = reports.map(r => r.gas_pollution);
    let oil_pressure = reports.map(r => r.oil_pressure);
    let vibration = reports.map(r => r.vibration);
    let working_time = reports.map(r => r.working_time);


    new Chart(document.getElementById('coolant_temp'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Температура охлаждающей жикости, С',
                data: coolant_temp,
                borderColor: '#42a5f5',
                backgroundColor: 'rgba(66,165,245,0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: -20,  max: 0,}
            }
        }
    });

    
    new Chart(document.getElementById('oil_pressure'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Давление масла, бар',
                data: oil_pressure,
                borderColor: '#42a5f5',
                backgroundColor: 'rgba(66,165,245,0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 3.5,  max: 6.5,}
            }
        }
    });

    new Chart(document.getElementById('gas_pollution'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Загазованность, %',
                data: gas_pollution,
                borderColor: '#42a5f5',
                backgroundColor: 'rgba(66,165,245,0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 0,  max: 20,}
            }
        }
    });

    new Chart(document.getElementById('dew_point'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Точка росы, С',
                data: dew_point,
                borderColor: '#42a5f5',
                backgroundColor: 'rgba(66,165,245,0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: -90,  max: -35,}
            }
        }
    });

    new Chart(document.getElementById('vibration'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Вибрация, %',
                data: vibration,
                borderColor: '#42a5f5',
                backgroundColor: 'rgba(66,165,245,0.1)',
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 0,  max: 20 ,}
            }
        }
    });