let reports = window.reports || [];

 let labels = reports.map(r => r.id); 
    let coolant_temp = reports.map(r => r.coolant_temp);
    let dew_point = reports.map(r => r.dew_point);
    let gas_pollution = reports.map(r => r.gas_pollution);
    let oil_pressure = reports.map(r => r.oil_pressure);
    let vibration = reports.map(r => r.vibration);
    let working_time = reports.map(r => r.working_time);



    function isLastValueError(data, min, max) {
    const lastValue = data[data.length - 1];
    return lastValue < min || lastValue > max;
    }

    let isError = isLastValueError(coolant_temp, -20, 0) ; 
    new Chart(document.getElementById('coolant_temp'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Температура охлаждающей жикости, С',
                data: coolant_temp,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: -40,  max: 10}
            }
        }
    });

    isError = isLastValueError(oil_pressure, 4, 6) ; 
    new Chart(document.getElementById('oil_pressure'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Давление масла, бар',
                data: oil_pressure,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 3.5,  max: 6.5}
            }
        }
    });

    isError = isLastValueError(gas_pollution, 0, 10) ; 
    new Chart(document.getElementById('gas_pollution'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Загазованность, %',
                data: gas_pollution,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 0,  max: 20}
            }
        }
    });


    isError = isLastValueError(dew_point, -90, -45) ; 
    new Chart(document.getElementById('dew_point'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Точка росы, С',
                data: dew_point,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3,
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: -90,  max: -35}
            }
        }
    });

    isError = isLastValueError(vibration, 0, 10) ;
    new Chart(document.getElementById('vibration'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Вибрация, %',
                data: vibration,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {display: false }},
                y: {min: 0,  max: 10 }
            }
        }
    });