import React from 'react';

class Results extends React.Component {

    render() {
        const prediction = this.props.location.prediction;

        return (
            <div>
                <h3> { prediction } </h3>
            </div>
        );
    }
}
export default Results;