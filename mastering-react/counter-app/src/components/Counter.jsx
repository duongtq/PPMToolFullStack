import React, { Component } from 'react'

class Counter extends Component {
    state = { 
        count: 0
    };

    render() {
        return (
            <div>
                <span className={this.getBadgeClasses()}>{this.formatCount()}</span>
                <button onClick={this.handleIncrement} className="btn btn-primary">Increment</button>
            </div>
        );
    }

    handleIncrement = () => {
        this.setState({count: this.state.count + 1});
    }

    getBadgeClasses() {
        let classes = "mr-2 badge ";
        if (this.state.count === 0) {
            classes+="badge-warning";
        } else {
            classes+="badge-primary";
        }

        return classes;
    }

    formatCount() {
        const { count } = this.state;
        if (count === 0) {
            return "Zero";
        }
        return count;
    }
}
 
export default Counter;